package com.revature.services;

import com.revature.dtos.UserDTO;
import com.revature.entities.Reservation;
import com.revature.entities.Room;
import com.revature.repos.ReservationRepo;
import com.revature.repos.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the service
 */
@Component
@Service
public class ReservationServiceImpl implements ReservationService {
    /**
     * Room Repository
     */
    @Autowired
    RoomRepo roomRepo;

    /**
     * Reservation Repository
     */
    @Autowired
    ReservationRepo reservationRepo;

    /**
     * Create the reservation in the database
     *
     * @param reservation Reservation to save
     *
     * @return Reservation with the new id
     */
    @Override
    public Reservation createReservation(Reservation reservation) {
        // check to make sure the room exists
        Room room = roomRepo.findById(reservation.getRoomId()).orElse(null);
        if (room == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such Room");
        }

        if (reservation.getTitle() == null || reservation.getTitle().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation must have a title");
        }

        // check to make sure the times given are valid
        if (reservation.getStartTime() > reservation.getEndTime()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid length of time, start time was after end time");
        }

        // check to make sure there isn't a conflict with other reservations
        Set<Reservation> conflicts = reservationRepo.findConflicts(reservation.getRoomId(), reservation.getStartTime(), reservation.getEndTime());
        if (conflicts.size() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Attempting to create reservation with conflicting time");
        }

        // sanitize before saving
        reservation.setReservationId(0);
        reservation.setStatus("reserved");
        return reservationRepo.save(reservation);
    }

    /**
     * Update the reservation to the new times
     *
     * @param reservation Reservation being updated
     * @param userDTO     User data from the authorization service
     *
     * @return Updated reservation
     */
    @Override
    public Reservation updateReservation(Reservation reservation, UserDTO userDTO) {
        // check to see if old reservation exists
        Reservation old = reservationRepo.findById(reservation.getReservationId()).orElse(null);
        if (old == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such reservation to update");
        }

        if (reservation.getStartTime() == 0) {
            reservation.setStartTime(old.getStartTime());
        }

        if (reservation.getEndTime() == 0) {
            reservation.setEndTime(old.getEndTime());
        }

        // check to make sure the times given are valid
        if (reservation.getStartTime() > reservation.getEndTime()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid length of time, start time was after end time");
        }

        // check to see if the userDTO owns the reservation and if they're an admin
        if (!old.getReserver().equals(userDTO.getEmail()) && !userDTO.getRole().equals("admin")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admins can cancel reservations that are not theirs");
        }

        if (reservation.getTitle() == null || reservation.getTitle().equals("")) {
            reservation.setTitle(old.getTitle());
        }

        // check to make sure there isn't a conflict with other reservations
        Set<Reservation> conflicts = reservationRepo.findConflicts(reservation.getRoomId(), reservation.getStartTime(), reservation.getEndTime());
        if (conflicts.size() > 0) {
            // we should be allowed to conflict with this current reservation because we are going to update it in the database, but haven't yet
            Reservation check = conflicts.iterator().next();
            if (check.getReservationId() != reservation.getReservationId()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Attempting to create reservation with conflicting time");
            }
            if (conflicts.size() > 1) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Attempting to create reservation with conflicting time");
            }
        }

        reservation.setRoomId(old.getRoomId());
        reservation.setStatus("reserved");
        return reservationRepo.save(reservation);
    }

    /**
     * Get all the reservations that are reserved given filter params
     *
     * @param roomId   Unique room id
     * @param start    Start time
     * @param end      End time
     * @param reserver Reserver email
     * @param status   Reservation status
     *
     * @return Set of the reserved rooms
     */
    @Override
    public Set<Reservation> getCustomReservations(Integer roomId, Long start, Long end, String reserver, String status) {
        Set<Reservation> reservations;
        long beginTime = start == null ? Long.MIN_VALUE : start;
        long endTime = end == null ? Long.MAX_VALUE : end;
        if (beginTime > endTime) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid time range given.");
        }
        reservations = new HashSet<>(reservationRepo.findByTimeRange(beginTime, endTime));

        reservations.removeIf(reservation -> (roomId != null && reservation.getRoomId() != roomId) ||
                (status != null && !reservation.getStatus().equals(status)) ||
                (reserver != null && !reservation.getReserver().equals(reserver)));
        return reservations;
    }


    /**
     * Cancel the reservation for that time frame
     *
     * @param reservationId Unique reservation id
     * @param userDTO       User data from the authorization service
     *
     * @return Updated reservation
     */
    @Override
    public Reservation cancelReservation(int reservationId, UserDTO userDTO) {
        // check to see if old reservation exists
        Reservation old = reservationRepo.findById(reservationId).orElse(null);
        if (old == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such reservation to update");
        }

        // already cancelled, just return
        if (old.getStatus().equals("cancelled")) {
            return old;
        }

        // check to see if the userDTO owns the reservation and if they're an admin
        if (!old.getReserver().equals(userDTO.getEmail()) && !userDTO.getRole().equals("admin")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admins can cancel reservations that are not theirs");
        }

        old.setStatus("cancelled");
        return reservationRepo.save(old);
    }

    /**
     * Get the reservation by the id
     *
     * @param reservationId Unique reservation id
     *
     * @return Reservation with the id
     */
    @Override
    public Reservation getReservationById(int reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId).orElse(null);
        if (reservation == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such reservation");
        return reservation;
    }
}
