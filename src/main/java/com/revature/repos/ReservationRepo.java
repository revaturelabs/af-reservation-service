package com.revature.repos;

import com.revature.entities.Reservation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
@Repository
public interface ReservationRepo {
    Set<Reservation> findByRoomIdAndStatus(int roomId, String status);
}
