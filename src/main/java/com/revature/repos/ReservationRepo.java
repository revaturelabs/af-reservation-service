package com.revature.repos;

import com.revature.entities.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
@Repository
public interface ReservationRepo extends CrudRepository<Reservation, Integer> {
    Set<Reservation> findByRoomIdAndStatus(int roomId, String status);
}
