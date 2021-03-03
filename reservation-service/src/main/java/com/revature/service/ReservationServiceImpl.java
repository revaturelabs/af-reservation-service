package com.revature.service;

import com.revature.model.Reservation;
import com.revature.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.revature.model.RoomType.VIRTUAL;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {

        this.repository = repository;
    }

    @Override
    public Reservation addReservation(Reservation reservation) {

        return repository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {

        return null;
    }

    @Override
    public void deleteReservation(Integer reservationId) {
        repository.deleteById(reservationId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    @Override
    public Reservation getReservationById(Integer reservationId) {
        return repository.getOne(reservationId);
    }

    @Override
    public void assignBatch(Integer reservationId, Integer batchId) {

    }

    @Override
    public List<Reservation> getAllReservationsByRoomId(Integer roomId) {
        return null;
    }

    @Override
    public List<Reservation> getAllAvailableRoomsByType(Integer BuildingId, String roomType) {
        return null;
    }

    @Override
    public List<Reservation> getTrainingStationReservations() {
        return repository.findAll().stream().filter(x -> x.getRoomType().equals(VIRTUAL)).collect(Collectors.toList());
    }

    @Override
	public boolean isValidReservation(Reservation reservation) {
//		TODO Complete method
//		List<Reservation> reservations = new ArrayList<>();
//		repository.findAllReservationsByRoomId(reservation.getRoomId()).forEach(
//				res -> reservations.add(res));
//		
//		for(Reservation res : reservations) {
//			if( reservation.getEndDate()  ) {
//				
//			}
//		}
		return false;
	}



}
