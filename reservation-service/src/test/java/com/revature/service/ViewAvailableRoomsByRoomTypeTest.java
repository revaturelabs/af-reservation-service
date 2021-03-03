package com.revature.service;

import com.revature.model.Reservation;
import com.revature.model.Room;
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
public class ViewAvailableRoomsByRoomTypeTest {

    private ReservationService service;
    private ReservationRepository repository;

    @Before
    public void preTest(){
        repository = Mockito.mock(ReservationRepository.class);
        service = new ReservationServiceImpl(repository);
    }

    @Test
    public void viewAvailableRoomsByRoomTypeTest(){
        Reservation reservation1 = new Reservation();
        reservation1.setRoomType(RoomType.PHYSICAL);
        reservation1.setBuildingId(101);
        reservation1.setRoomId(301);

        Reservation reservation2 = new Reservation();
        reservation2.setRoomType(RoomType.PHYSICAL);
        reservation2.setBuildingId(101);
        reservation2.setRoomId(302);

        Reservation reservation3 = new Reservation();
        reservation3.setRoomType(RoomType.PHYSICAL);
        reservation3.setBuildingId(101);
        reservation3.setRoomId(303);

        List<Reservation> reservationList =
                new ArrayList<>(Arrays.asList(reservation1, reservation2,reservation3));

      //  Mockito.when(repository.findAllAvailableRoomsByType(anyInt(),anyString()))
      //          .thenReturn(reservationList);

        List<Room> availableMeetingRooms = service.getAllAvailableMeetingRooms(101,"start","end");

        assertNotNull(availableMeetingRooms);
        assertEquals(3,availableMeetingRooms.size());
//        assertEquals(Integer.valueOf(101), availableMeetingRooms.get(0).getBuilding());
//        assertEquals(Integer.valueOf(303),availableMeetingRooms.get(0).getRoomId());
//        assertEquals(RoomType.PHYSICAL,availableMeetingRooms.get(0).getRoomType());
  //      verify(repository,times(1)).findAllAvailableRoomsByType(anyInt(),anyString());

    }

}