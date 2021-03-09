package com.revature.service;

import com.revature.model.Reservation;
import com.revature.util.RoomType;
import com.revature.repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledReservationsTest {

    private ReservationService service;
    private ReservationRepository repository;

    @Before
    public void preTest(){
        repository = Mockito.mock(ReservationRepository.class);
        service = new ReservationServiceImpl(repository);
    }

    @Test
    public void scheduledReservationsByRoomTest() {

        Reservation reservation1 = new Reservation(
                1,1,1,1,1,RoomType.PHYSICAL,"Revature CEO",
                "01-17-2021 09:00","01-17-2021 17:00");
        Reservation reservation2 = new Reservation(
                2,1,1,1,1,RoomType.PHYSICAL,"Revature CEO",
                "01-18-2021 09:00","01-18-2021 17:00");
        Reservation reservation3 = new Reservation(
                3,1,1,1,1,RoomType.PHYSICAL,"Revature CEO",
                "01-19-2021 09:00","01-19-2021 17:00");

        List<Reservation> reservationList =
                new ArrayList<>(Arrays.asList(reservation1, reservation2,reservation3));

        Mockito.when(repository.findAllReservationsByRoomId(anyInt()))
                .thenReturn(reservationList);

        List<Reservation> allReservations = service.getAllReservationsByRoomId(1);

        assertNotNull(allReservations);
        assertEquals(3,allReservations.size());
        assertEquals("Revature CEO", allReservations.get(0).getReserver());
        assertEquals(Integer.valueOf(1), allReservations.get(0).getBuildingId());
        assertEquals(Integer.valueOf(1),allReservations.get(0).getRoomId());
        assertEquals(RoomType.PHYSICAL,allReservations.get(0).getRoomType());
        verify(repository,times(1)).findAllReservationsByRoomId(anyInt());
    }

    @Test
    public void emptyReservationsByRoomTest(){

        Mockito.when(repository.findAllReservationsByRoomId(anyInt()))
                .thenReturn(new ArrayList<>());
        List<Reservation> allReservations = service.getAllReservationsByRoomId(any());
        assertEquals(0,allReservations.size());
    }

    @Test
    public void getReservationsByInvalidId(){
        Reservation reservation1 = new Reservation(
                1,1,1,1,1,RoomType.PHYSICAL,"Revature CEO",
                "01-17-2021 09:00","01-17-2021 17:00");
        Reservation reservation2 = new Reservation(
                2,1,1,1,1,RoomType.PHYSICAL,"Revature CEO",
                "01-18-2021 09:00","01-18-2021 17:00");

        List<Reservation> reservationList =
                new ArrayList<>(Arrays.asList(reservation1, reservation2));

        Mockito.when(repository.findAllReservationsByRoomId(1))
                .thenReturn(reservationList);

        List<Reservation> allReservations = service.getAllReservationsByRoomId(2);
        assertTrue(allReservations.size() == 0);

    }
}
