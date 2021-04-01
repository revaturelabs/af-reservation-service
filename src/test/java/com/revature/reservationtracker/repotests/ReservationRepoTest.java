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
        Set<Reservation> reservations = reservationRepo.findByRoomIdAndStatus(1, "reserved");
        Assertions.assertNotEquals(0, reservations.size());
        for (Reservation r : reservations) {
            Assertions.assertEquals("reserved", r.getStatus());
            Assertions.assertEquals(1, r.getRoomId());
        }
    }

    @Test
    void find_by_room_id_and_status_cancelled() {
        Set<Reservation> reservations = reservationRepo.findByRoomIdAndStatus(1, "cancelled");
        Assertions.assertNotEquals(0, reservations.size());
        for (Reservation r : reservations) {
            Assertions.assertEquals(1, r.getRoomId());
            Assertions.assertEquals("cancelled", r.getStatus());
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
