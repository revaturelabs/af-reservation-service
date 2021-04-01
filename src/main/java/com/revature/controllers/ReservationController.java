package com.revature.controllers;

import com.revature.dtos.ReservationDTO;
import com.revature.dtos.UserDTO;
import com.revature.entities.Reservation;
import com.revature.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@RestController
@CrossOrigin
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/rooms/{roomId}/reservations")
    public ResponseEntity<Reservation> createReservation(@PathVariable int roomId, @RequestBody ReservationDTO reservationDTO){
        Reservation reservation = reservationTransfer(roomId, reservationDTO);

        reservationService.createReservation(reservation);
        return ResponseEntity.status(201).body(reservation);
    }

    @GetMapping("/rooms/{roomId}/reservations")
    public ResponseEntity<Set<Reservation>> getReservations(@PathVariable int roomId){
        Set<Reservation> reservations = reservationService.getActiveReservationsByRoomId(roomId);
        return ResponseEntity.status(200).body(reservations);
    }

    @GetMapping("/rooms/{roomId}/reservations/{reservationId}")
    public ResponseEntity<Reservation> getReservation(@PathVariable int roomId, @PathVariable int reservationId){
        Reservation reservation = reservationService.getReservationById(reservationId);
        reservation.setRoomId(roomId);
        return ResponseEntity.status(200).body(reservation);
    }

    @PatchMapping("/rooms/{roomId}/reservations/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable int roomId,
                                                         @PathVariable int reservationId,
                                                         @RequestParam(name = "action", defaultValue = "") String action,
                                                         @RequestBody ReservationDTO reservationDTO){

        Reservation reservation = reservationTransfer(roomId, reservationDTO);

        // placeholder
        UserDTO userDTO = new UserDTO(1, "test@email.revature.com", "admin");
        if (action.equals("cancel")){
            reservationService.cancelReservation(reservationId, userDTO);
        }
        else {
            reservationService.updateReservationTime(reservation, userDTO);
        }
        return ResponseEntity.status(200).body(reservation);
    }

    public Reservation reservationTransfer(int roomId, ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setRoomId(roomId);
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setReservationId(reservationDTO.getRoomId());
        reservation.setReserver(reservationDTO.getReserver());
        reservation.setStatus(reservationDTO.getStatus());
        return reservation;
    }

}
