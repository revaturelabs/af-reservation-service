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
    void find_by_time_range_all() {
        Set<Reservation> reservations = reservationRepo.findByTimeRange(Long.MIN_VALUE, Long.MAX_VALUE);
        Assertions.assertTrue(reservations.size() == 6);
    }

    @Test
    void find_by_time_range() {
        Set<Reservation> reservations = reservationRepo.findByTimeRange(3601, Long.MAX_VALUE);
        Assertions.assertTrue(reservations.size() == 5);
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
