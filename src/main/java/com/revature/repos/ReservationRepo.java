package com.revature.repos;

import com.revature.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Manager of the entity repository. Executes queries specified and implicit ones.
 */
@Component
@Repository
public interface ReservationRepo extends CrudRepository<Reservation, Integer> {

    /** Gets all the reservation in a room of a certain status where the start and end times are within a specific range */
    @Query("select r from Reservation r WHERE ((r.startTime >= ?1 and r.startTime <= ?2) or (r.endTime >= ?1 and r.endTime <= ?2) or (r.startTime <= ?1 and r.endTime >= ?2))")

    Set<Reservation> findByTimeRange(long startTime, long endTime);

    /**
     * Find the conflicts in a time space
     */
    @Query("select r from Reservation r WHERE r.status = 'reserved' and r.roomId = ?1 and ((r.startTime < ?2 and ?2 < r.endTime) or (?2 < r.startTime and r.startTime < ?3))")
    Set<Reservation> findConflicts(int roomId, long start, long end);
}
