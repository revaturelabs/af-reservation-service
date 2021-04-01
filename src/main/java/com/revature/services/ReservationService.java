package com.revature.services;

import com.revature.dtos.UserDTO;
import com.revature.entities.Reservation;

import java.util.Set;

/** Interface for the reservation service CRUD methods*/
public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation updateReservationTime(Reservation reservation, UserDTO userDTO);

    Set<Reservation> getActiveReservationsByRoomId(int roomId);

    Reservation cancelReservation(int reservationId, UserDTO userDTO);

    Reservation getReservationById(int reservationId);
}
