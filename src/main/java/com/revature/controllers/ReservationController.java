package com.revature.controllers;

import com.revature.aspects.Verify;
import com.revature.dtos.ReservationDTO;
import com.revature.dtos.UserDTO;
import com.revature.entities.Reservation;
import com.revature.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

/** Handles incoming requests through the specified URIs */
@Component
@RestController
@CrossOrigin
public class ReservationController {

    /** Reservation Service */
    @Autowired
    ReservationService reservationService;

    /**
     * Create a reservation in a room
     * @param roomId Unique room Id
     * @param reservationDTO Reservation that is sent in
     * @return the reservation that was created and status
     */
    @Verify
    @PostMapping("/rooms/{roomId}/reservations")
    public ResponseEntity<Reservation> createReservation(UserDTO userDTO,
                                                         @PathVariable int roomId,
                                                         @RequestBody ReservationDTO reservationDTO) {
        // transfer the DTO into a reservation object
        Reservation reservation = reservationTransfer(roomId, reservationDTO);
        // persist the reserver Id and email of user
        reservation.setReservationId(0);
        reservation.setReserver(userDTO.getEmail());

        // create reservation
        reservationService.createReservation(reservation);
        return ResponseEntity.status(201).body(reservation);
    }

    /**
     * Get the reservations by room id
     * @param roomId Unique room id
     * @param begin Specify the earliest time to return reservations
     * @param end Specify the latest time to return reservations
     * @return The reservations of the specified room and status
     */
    @Verify
    @GetMapping("/rooms/{roomId}/reservations")
    public ResponseEntity<Set<Reservation>> getReservations(UserDTO userDTO,
                                                            @PathVariable int roomId,
                                                            @RequestParam(name = "start", required = false) Long begin,
                                                            @RequestParam(name = "end", required = false) Long end) {
        Set<Reservation> reservations = reservationService.getActiveReservationsByRoomId(roomId, begin, end);
        return ResponseEntity.status(200).body(reservations);
    }

    /**
     * Get the reservation with a specified id
     * @param roomId Room the reservation is in
     * @param reservationId Unique the reservation id
     * @return The specified reservation and status
     */
    @Verify
    @GetMapping("/rooms/{roomId}/reservations/{reservationId}")
    public ResponseEntity<Reservation> getReservation(UserDTO userDTO,
                                                      @PathVariable int roomId,
                                                      @PathVariable int reservationId) {
        // get reservation By Id
        Reservation reservation = reservationService.getReservationById(reservationId);
        // persist the room id and user email
        reservation.setRoomId(roomId);
        reservation.setReserver(userDTO.getEmail());

        return ResponseEntity.status(200).body(reservation);
    }

    /**
     * Update/patch the necessary data in the database. Query param will cancel the reservation
     * @param roomId Unique room Id
     * @param reservationId Unique Reservation Id
     * @param action Query param (default is "")
     * @param reservationDTO Reservation that is sent in
     * @return The updated reservation and status
     */
    @Verify
    @PatchMapping("/rooms/{roomId}/reservations/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(UserDTO userDTO,
                                                         @PathVariable int roomId,
                                                         @PathVariable int reservationId,
                                                         @RequestParam(name = "action", defaultValue = "") String action,
                                                         @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation; // reservation

        // if the query param is 'cancel'
        if (action.equals("cancel")) {
            reservation = reservationService.cancelReservation(reservationId, userDTO);
        } else {
            //check if body was provided
            if(reservationDTO == null) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Must provided update body");
            }
            // update the reservation
            // transfer the data into a reservation object
            reservation = reservationTransfer(roomId, reservationDTO);
            // persist the reserver and id given in path
            reservation.setReserver(userDTO.getEmail());
            reservation.setReservationId(reservationId);

            // update
            reservationService.updateReservation(reservation, userDTO);
        }
        return ResponseEntity.status(200).body(reservation);
    }

    /**
     * Helper method to switch the data transfer object into a Reservation entity object that is persisted
     * @param roomId Unique room id
     * @param reservationDTO Reservation that is sent in
     * @return The new reservation object
     */
    public Reservation reservationTransfer(int roomId, ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setRoomId(roomId);
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setTitle(reservationDTO.getTitle());
        return reservation;
    }

}
