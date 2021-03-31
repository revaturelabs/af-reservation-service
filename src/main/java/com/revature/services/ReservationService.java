package com.revature.services;

import com.revature.entities.Reservation;

import java.util.Set;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation updateReservationTime(Reservation reservation);
    Set<Reservation> getActiveReservationsByRoomId(int roomId);
    Reservation cancelReservation(int reservationId);
    Reservation getReservationById(int reservationId);
}
