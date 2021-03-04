package com.revature.service;

import com.revature.controller.ReservationController;
import com.revature.model.Reservation;
import com.revature.model.Room;
import com.revature.model.RoomType;
import com.revature.repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewAvailableMeetingRooms {

    @Mock
    private ReservationService service;
    ReservationController controller;

    @Before
    public void preTest() {
        MockitoAnnotations.openMocks(this);
        controller = new ReservationController(service);
    }

    @Test
    public void viewAvailableRoomsByRoomTypeTest(){

        Room room1 = new Room(101);
        Room room2 = new Room(102);
        Room room3 = new Room(103);

        List<Room> roomList = new ArrayList<>(Arrays.asList( room1, room2, room3));

        Mockito.when(service.getAllAvailableMeetingRooms(anyInt(),"11-22-1999 11:30","11-23-1999 11:30")).thenReturn(roomList);

        ResponseEntity<List<Room>> listResponseEntity = controller.getAllAvailableMeetingRooms(1, "start", "end");
        List<Room> body = listResponseEntity.getBody();

        System.out.println(body);




    }

}