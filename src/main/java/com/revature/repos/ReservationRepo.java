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

    @Query("select r from Reservation r WHERE r.status = 'reserved' and r.roomId = ?1 and ((r.startTime < ?2 and ?2 < r.endTime) or (?2 < r.startTime and r.startTime < ?3))")
    Set<Reservation> findConflicts(int roomId, long start, long end);
}
