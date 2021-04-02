package com.revature.reservationtracker.repotests;

import com.revature.entities.Reservation;
import com.revature.repos.ReservationRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ReservationRepoTest {
    @Autowired
    ReservationRepo reservationRepo;

    @Test
    void find_by_room_id_and_status() {
        Set<Reservation> reservations = reservationRepo.findByRoomIdAndStatusWhereStartTimeBetweenOrEndTimeBetween(1, "reserved", Long.MIN_VALUE,Long.MAX_VALUE);
        Assertions.assertNotEquals(0, reservations.size());
        for (Reservation r : reservations) {
            Assertions.assertEquals("reserved", r.getStatus());
            Assertions.assertEquals(1, r.getRoomId());
        }
    }

    @Test
    void find_by_room_id_and_status_cancelled() {
        Set<Reservation> reservations = reservationRepo.findByRoomIdAndStatusWhereStartTimeBetweenOrEndTimeBetween(1, "cancelled", Long.MIN_VALUE, Long.MAX_VALUE);
        Assertions.assertNotEquals(0, reservations.size());
        for (Reservation r : reservations) {
            Assertions.assertEquals(1, r.getRoomId());
            Assertions.assertEquals("cancelled", r.getStatus());
        }
    }

    @Test
    void find_by_room_id_and_status_with_time_range() {
        long start = -5;
        long end = 10000;
        Set<Reservation> reservations = reservationRepo.findByRoomIdAndStatusWhereStartTimeBetweenOrEndTimeBetween(1, "reserved", start, end);
        Assertions.assertNotEquals(0, reservations.size());
        for (Reservation r : reservations) {
            Assertions.assertEquals("reserved", r.getStatus());
            Assertions.assertEquals(1, r.getRoomId());
            Assertions.assertTrue((r.getStartTime() < end && r.getStartTime() > start) || (r.getEndTime() < end && r.getEndTime() > start));
        }
    }

    @Test
    void find_by_room_id_and_status_with_time_range_no_begin() {
        long end = 10000;
        Set<Reservation> reservations = reservationRepo.findByRoomIdAndStatusWhereStartTimeBetweenOrEndTimeBetween(1, "reserved", Long.MIN_VALUE, end);
        Assertions.assertNotEquals(0, reservations.size());
        for (Reservation r : reservations) {
            Assertions.assertEquals("reserved", r.getStatus());
            Assertions.assertEquals(1, r.getRoomId());
            Assertions.assertTrue(r.getStartTime() < end || r.getEndTime() < end);
        }
    }

    @Test
    void find_by_room_id_and_status_with_time_range_no_end() {
        long start = -5;
        Set<Reservation> reservations = reservationRepo.findByRoomIdAndStatusWhereStartTimeBetweenOrEndTimeBetween(1, "reserved", start, Long.MAX_VALUE);
        Assertions.assertNotEquals(0, reservations.size());
        for (Reservation r : reservations) {
            Assertions.assertEquals("reserved", r.getStatus());
            Assertions.assertEquals(1, r.getRoomId());
            Assertions.assertTrue(r.getStartTime() > start || r.getEndTime() > start);
        }
    }

    @Test
    void find_conflicts_none() {
        Set<Reservation> reservations = reservationRepo.findConflicts(2, 5000, 7000);
        Assertions.assertEquals(0, reservations.size());
    }

    // in data base                                  |--------------------------|
    // should find conflict for this          |-------------------|
    @Test
    void find_conflict_before_case() {
        Set<Reservation> reservations = reservationRepo.findConflicts(1, 3650, 7250);
        Assertions.assertNotEquals(0, reservations.size());
        System.out.println(reservations);
    }

    // in data base                                  |--------------------------|
    // should find conflict for this          |------------------------------------|
    @Test
    void find_conflicts_envelops_case() {
        Set<Reservation> reservations = reservationRepo.findConflicts(2, 1000, 5000);
        Assertions.assertNotEquals(0, reservations.size());
        System.out.println(reservations);
    }

    // in data base                                  |--------------------------|
    // should find conflict for this                            |------------------------|
    @Test
    void find_conflicts_after_case() {
        Set<Reservation> reservations = reservationRepo.findConflicts(1, 5000, 8000);
        Assertions.assertNotEquals(0, reservations.size());
        System.out.println(reservations);
    }

    // in data base                                  |--------------------------|
    // should find conflict for this                    |-----------------|
    @Test
    void find_conflicts_internal_case() {
        Set<Reservation> reservations = reservationRepo.findConflicts(1, 4000, 6000);
        Assertions.assertNotEquals(0, reservations.size());
        System.out.println(reservations);
    }
}
