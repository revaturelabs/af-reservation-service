package com.revature.repos;

import com.revature.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
@Repository
public interface ReservationRepo extends CrudRepository<Reservation, Integer> {
    Set<Reservation> findByRoomIdAndStatus(int roomId, String status);

    @Query("SELECT * FROM Reservation WHERE Reservation.status = 'reserved' and Reservation.roomId = ?3 and ((Reservation.startTime < ?2 and not Reservation.endTime < ?1) or (Reservation.endTime > ?1 and not Reservation.startTime > ?2))")
    Set<Reservation> findByRoomIdWithTimeCheck(long start, long end, int roomId);
}
