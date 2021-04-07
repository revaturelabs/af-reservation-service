package com.revature.services;

import com.revature.dtos.UserDTO;
import com.revature.entities.Reservation;

import java.util.Set;

/** Interface for the reservation service CRUD methods*/
public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(Reservation reservation, UserDTO userDTO);

    Reservation cancelReservation(int reservationId, UserDTO userDTO);

    Reservation getReservationById(int reservationId);

    Set<Reservation> getCustomReservations(Integer roomId, Long start, Long end, String reserver, String status);
}
