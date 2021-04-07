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
     * @param reservationDTO Reservation that is sent in
     * @return the reservation that was created and status
     */
    @Verify
    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(UserDTO userDTO,
                                                         @RequestBody ReservationDTO reservationDTO) {
        // transfer the DTO into a reservation object
        Reservation reservation = reservationTransfer(reservationDTO);
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
     * @param start Specify the earliest time to return reservations
     * @param end Specify the latest time to return reservations
     * @param reserver Specify the reserver to be filtered for in reservations
     * @param status Specify the status to be filtered for in reservations
     * @return The reservations of the specified room, time, reserver, and/or status
     */
    @Verify
    @GetMapping("/reservations")
    public ResponseEntity<Set<Reservation>> getReservations(UserDTO userDTO,
                                                            @RequestParam(name = "roomId", required = false) Integer roomId,
                                                            @RequestParam(name = "start", required = false) Long start,
                                                            @RequestParam(name = "end", required = false) Long end,
                                                            @RequestParam(name = "reserver", required = false) String reserver,
                                                            @RequestParam(name = "status", required = false) String status) {
        Set<Reservation> reservations;
        reservations = reservationService.getCustomReservations(roomId, start, end, reserver, status);
        return ResponseEntity.status(200).body(reservations);
    }

    /**
     * Get the reservation with a specified id
     * @param reservationId Unique the reservation id
     * @return The specified reservation and status
     */
    @Verify
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<Reservation> getReservation(UserDTO userDTO,
                                                      @PathVariable int reservationId) {
        // get reservation By Id
        Reservation reservation = reservationService.getReservationById(reservationId);
        // persist the room id and user email
        reservation.setReserver(userDTO.getEmail());

        return ResponseEntity.status(200).body(reservation);
    }

    /**
     * Update/patch the necessary data in the database. Query param will cancel the reservation
     * @param reservationId Unique Reservation Id
     * @param action Query param (default is "")
     * @param reservationDTO Reservation that is sent in
     * @return The updated reservation and status
     */
    @Verify
    @PatchMapping("/reservations/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(UserDTO userDTO,
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
            reservation = reservationTransfer(reservationDTO);
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
     * @param reservationDTO Reservation that is sent in
     * @return The new reservation object
     */
    public Reservation reservationTransfer(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setRoomId(reservationDTO.getRoomId());
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setTitle(reservationDTO.getTitle());
        return reservation;
    }

}
