package com.revature.service;

import com.revature.model.Reservation;
import com.revature.repository.ReservationRepository;
import com.revature.service.ReservationService;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }
    @Override
    public Reservation addReservation(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return null;
    }

    @Override
    public void deleteReservation(Integer reservationId) {

    }

    @Override
    public List<Reservation> getAllReservations() {
        return null;
    }

    @Override
    public void assignBatch(Integer batchId) {

    }

    @Override
    public List<Reservation> getAllReservationsByRoomId(Integer buildingId, Integer roomId) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservationsByRoomType(Integer buildingId, String roomType) {
        return null;
    }


}
