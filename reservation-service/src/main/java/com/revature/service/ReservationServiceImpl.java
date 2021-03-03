package com.revature.service;

import com.revature.model.Reservation;
import com.revature.model.Room;
import com.revature.repository.ReservationRepository;
import org.springframework.stereotype.Service;
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
		return repository.findAllReservationsByRoomId(roomId);
	}

	@Override
	public List<Room> getAllAvailableMeetingRooms(Integer BuildingId, String startDate, String endDate) {
		// make request to locations service to get the list of rooms by building id
		// from the list, extract all the rooms that have not been reserved yet in a specific time frame, filter by startDate and endDate
		// filter the list further by room occupation (by meeting) and return the list of rooms
		return null;
	}

	@Override
	public List<Reservation> getTrainingStationReservations() {
		return repository.findAll().stream().filter(x -> x.getRoomType().equals(VIRTUAL)).collect(Collectors.toList());
	}

	@Override
	public boolean isValidReservation(Reservation reservation) {
		
		//create result value for neatness this method assumes the proposed reservation time is 
		//valid
		boolean result = true;
		
		//list for reservations
		List<Reservation> reservations = new ArrayList<>();
		
		//populate list with all reservations with a matching room number
		repository.findAllReservationsByRoomId(reservation.getRoomId()).forEach(
				res -> reservations.add(res));

		//for each reservation in the list of shared room numbers check to see if the reservations
		//start date and end date fall conflict with any of the already listed reservations
		for(Reservation res : reservations) {
			
			//reject if the proposed start date is before a listed start date, and the proposed
			//end date is after the proposed start date or if the proposed start date is before 
			//a listed end date and the proposed end date is after a listed end date or
			// if the proposed start date and end date are in between a listed start and end dates
			if( ( reservation.getStartDate() > res.getStartDate() && reservation.getEndDate() > 
			res.getStartDate() ) || ( reservation.getStartDate() < res.getStartDate() && 
					reservation.getEndDate() > res.getEndDate() ) || ( reservation.getStartDate() > 
					res.getEndDate() && reservation.getEndDate() > res.getEndDate() ) ) {
				
				//reservation is invalid result is false
				result = false;

			}
		}
		
		return result;
	}



}
