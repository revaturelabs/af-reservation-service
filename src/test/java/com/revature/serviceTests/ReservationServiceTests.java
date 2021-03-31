package com.revature.serviceTests;

import com.revature.entities.Reservation;
import com.revature.entities.User;
import com.revature.repos.ReservationRepo;
import com.revature.repos.RoomRepo;
import com.revature.services.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {
    @Mock
    static ReservationRepo reservationRepo;

    @Mock
    static RoomRepo roomRepo;

    @InjectMocks
    static ReservationService service;


    static User user = new User(1, "john.doe@revature.com", "trainer");

    @Test
    void create_reservation() {
        Mockito.when(reservationRepo.save(any())).then(returnsFirstArg());
        Reservation reservation = new Reservation();
        reservation.setReservationId(505);
        reservation.setReserver(user.getEmail());
        reservation.setStartTime(System.currentTimeMillis()/1000L);
        reservation.setEndTime((System.currentTimeMillis()/1000L) + 3600);
        reservation.setStatus("jdjdjdjdjd");
        reservation.setRoomId(0);

        Reservation newReservation = service.createReservation(reservation);
        Assertions.assertEquals(0, newReservation.getReservationId());
        Assertions.assertEquals("reserved", newReservation.getStatus());
    }

    @Test
    void create_reservation_bad_time_period() {
        Mockito.when(reservationRepo.save(any())).then(returnsFirstArg());
        Reservation reservation = new Reservation();
        reservation.setReservationId(505);
        reservation.setReserver(user.getEmail());
        reservation.setStartTime(System.currentTimeMillis()/1000L);
        reservation.setEndTime((System.currentTimeMillis()/1000L) - 3600);
        reservation.setStatus("reserved");
        reservation.setRoomId(0);

        Assertions.assertThrows(ResponseStatusException.class, ()-> service.createReservation(reservation));
    }

    @Test
    void update_reservation_time() {
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setReserver(user.getEmail());
        reservation.setStartTime(System.currentTimeMillis()/1000L);
        reservation.setEndTime((System.currentTimeMillis()/1000L) + 3600);
        reservation.setStatus("reserved");
        reservation.setRoomId(2);

        Mockito.when(reservationRepo.findById(anyInt()).orElse(null)).thenReturn(reservation);
        Mockito.when(reservationRepo.save(any())).then(returnsFirstArg());

        Reservation update = new Reservation();
        update.setReservationId(1);
        update.setReserver(user.getEmail());
        long newStartTime = System.currentTimeMillis()/1000L + 3600;
        long newEndTime = newStartTime + 3600;
        update.setStartTime(newStartTime);
        reservation.setEndTime(newEndTime);
        reservation.setStatus("canceled");
        reservation.setRoomId(4);

        Reservation updated = service.updateReservationTime(update);
        Assertions.assertEquals(newStartTime, updated.getStartTime());
        Assertions.assertEquals(newEndTime, updated.getEndTime());
        Assertions.assertEquals(2, updated.getRoomId());
        Assertions.assertEquals("reserved", updated.getStatus());
    }

    @Test
    void get_active_reservations_by_room_id() {
        Set<Reservation> reservationSet = new HashSet<>();
        Reservation reservation = new Reservation();
        reservation.setReservationId(2);
        reservation.setReserver(user.getEmail());
        reservation.setStartTime(System.currentTimeMillis()/1000L);
        reservation.setEndTime((System.currentTimeMillis()/1000L) + 3600);
        reservation.setStatus("reserved");
        reservation.setRoomId(1);

        reservationSet.add(reservation);

        reservation = new Reservation();
        reservation.setReservationId(4);
        reservation.setReserver(user.getEmail());
        reservation.setStartTime(System.currentTimeMillis()/1000L+ 3700);
        reservation.setEndTime((System.currentTimeMillis()/1000L) + 7300);
        reservation.setStatus("reserved");
        reservation.setRoomId(1);

        reservationSet.add(reservation);

        Mockito.when(reservationRepo.findByRoomIdAndStatus(1, "reserved")).thenReturn(reservationSet);

        Set<Reservation> check = service.getActiveReservationsByRoomId(1);
        Assertions.assertEquals(2, check.size());
    }

}
