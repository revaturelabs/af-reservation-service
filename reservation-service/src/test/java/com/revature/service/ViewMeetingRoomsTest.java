package com.revature.service;

import com.revature.model.Reservation;
import com.revature.model.RoomType;
import com.revature.repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewMeetingRoomsTest {

    private ReservationService service;
    private ReservationRepository repository;

    @Before
    public void preTest(){
        repository = Mockito.mock(ReservationRepository.class);
        service = new ReservationServiceImpl(repository);
    }

    @Test
    public void viewRoomsByRoomTypeTest(){
        Reservation reservation1 = new Reservation();
        reservation1.setReservationId(1);
        reservation1.setRoomType(RoomType.PHYSICAL);
        reservation1.setReserver("Revature CEO");
        reservation1.setBuildingId(101);
        reservation1.setRoomId(303);

        Reservation reservation2 = new Reservation();
        reservation2.setReservationId(2);
        reservation2.setRoomType(RoomType.PHYSICAL);
        reservation2.setReserver("Revature CEO");
        reservation2.setBuildingId(101);
        reservation2.setRoomId(303);

        Reservation reservation3 = new Reservation();
        reservation3.setReservationId(3);
        reservation3.setRoomType(RoomType.PHYSICAL);
        reservation3.setReserver("Revature CEO");
        reservation3.setBuildingId(101);
        reservation3.setRoomId(303);

        List<Reservation> reservationList =
                new ArrayList<>(Arrays.asList(reservation1, reservation2,reservation3));

        Mockito.when(repository.findAllReservationsByRoomType(anyInt(),anyString()))
                .thenReturn(reservationList);

        List<Reservation> allReservationsByRoomType = service.getAllReservationsByRoomType(101,"PHYSICAL");

        assertNotNull(allReservationsByRoomType);
        assertEquals(2,allReservationsByRoomType.size());
        assertEquals("Revature CEO", allReservationsByRoomType.get(0).getReserver());
        assertEquals(Integer.valueOf(101), allReservationsByRoomType.get(0).getBuildingId());
        assertEquals(Integer.valueOf(303),allReservationsByRoomType.get(0).getRoomId());
        assertEquals(RoomType.PHYSICAL,allReservationsByRoomType.get(0).getRoomType());
        verify(repository,times(1)).findAllReservationsByRoomType(anyInt(),anyString());

    }

}