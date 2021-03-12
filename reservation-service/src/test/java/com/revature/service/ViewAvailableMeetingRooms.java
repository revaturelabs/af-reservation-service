package com.revature.service;
import com.revature.dto.RoomDTO;
import com.revature.model.Reservation;

import com.revature.repository.ReservationRepository;

import com.revature.util.RoomType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
//@RunWith/(MockitoJUnitRunner.class)
public class ViewAvailableMeetingRooms {

    private Reservation reservation;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ReservationRepository repository;

    @InjectMocks
    @Autowired
    private ReservationServiceImpl reservationService;

    private RoomDTO rooms;

    @Before
    public void setUp() throws Exception {

        reservationService.setRestTemplate(restTemplate);
    }

    @Test
    public void viewAvailableRoomsByRoomTypeTest(){

        RoomDTO room1 = new RoomDTO();
        room1.setId(1);
        room1.setType("PHYSICAL");
        room1.setOccupation("MEETING");

        RoomDTO room2 = new RoomDTO();
        room2.setId(2);
        room2.setType("PHYSICAL");
        room2.setOccupation("MEETING");

        RoomDTO room3 = new RoomDTO();
        room3.setId(3);
        room3.setType("PHYSICAL");
        room3.setOccupation("MEETING");

        Reservation r1 = new Reservation(1,1,1,1,1,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 07:00","01-17-2021 10:00");
        Reservation r2 = new Reservation(1,1,1,1,1,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 10:00","01-17-2021 11:00");
        Reservation r3 = new Reservation(1,1,1,1,2,RoomType.PHYSICAL,"Reserver",
                "01-18-2021 09:00","01-18-2021 11:00");
        Reservation r4 = new Reservation(1,1,1,1,2,RoomType.PHYSICAL,"Reserver",
                "01-18-2021 12:00","01-18-2021 13:00");


        Mockito.when(repository.findAllReservationsByRoomId(1)).thenReturn(Arrays.asList(r1, r2));
        Mockito.when(repository.findAllReservationsByRoomId(2)).thenReturn(Arrays.asList(r3,r4));

        RoomDTO[] allRooms = {room1, room2, room3};

        ResponseEntity<RoomDTO[]> resp = new ResponseEntity<>(allRooms,HttpStatus.OK);
        Mockito.when( restTemplate.getForEntity(
                ArgumentMatchers.anyString(),
                Mockito.eq( RoomDTO[].class ) ))
                .thenReturn(resp);

        List<RoomDTO> availableRooms = reservationService.getAllAvailableMeetingRooms( 1,"01-17-2021 09:00","01-17-2021 09:30" );

//        System.out.println(availableRooms);
//        System.out.println(availableRooms.size());

        assertEquals(2, availableRooms.toArray().length);

    }
    
    @Test
    public void viewAvailableRoomsByRoomTypeTestMatchingDates(){

        RoomDTO room1 = new RoomDTO();
        room1.setId(1);
        room1.setType("PHYSICAL");
        room1.setOccupation("MEETING");

        RoomDTO room2 = new RoomDTO();
        room2.setId(2);
        room2.setType("PHYSICAL");
        room2.setOccupation("MEETING");

        RoomDTO room3 = new RoomDTO();
        room3.setId(3);
        room3.setType("PHYSICAL");
        room3.setOccupation("MEETING");

        Reservation r1 = new Reservation(1,1,1,1,1,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 07:00","01-17-2021 10:00");
        Reservation r2 = new Reservation(1,1,1,1,1,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 10:00","01-17-2021 11:00");
        Reservation r3 = new Reservation(1,1,1,1,2,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 09:00","01-17-2021 11:00");
        Reservation r4 = new Reservation(1,1,1,1,2,RoomType.PHYSICAL,"Reserver",
                "01-18-2021 12:00","01-18-2021 13:00");


        Mockito.when(repository.findAllReservationsByRoomId(1)).thenReturn(Arrays.asList(r1, r2));
        Mockito.when(repository.findAllReservationsByRoomId(2)).thenReturn(Arrays.asList(r3,r4));

        RoomDTO[] allRooms = {room1, room2, room3};

        ResponseEntity<RoomDTO[]> resp = new ResponseEntity<>(allRooms,HttpStatus.OK);
        Mockito.when( restTemplate.getForEntity(
                ArgumentMatchers.anyString(),
                Mockito.eq( RoomDTO[].class ) ))
                .thenReturn(resp);

        List<RoomDTO> availableRooms = reservationService.getAllAvailableMeetingRooms( 1,"01-17-2021 09:00","01-17-2021 11:00" );

        assertEquals(1, availableRooms.toArray().length);

    }
    
    @Test
    public void viewAvailableRoomsByRoomTypeTestStartDateConflict(){

        RoomDTO room1 = new RoomDTO();
        room1.setId(1);
        room1.setType("PHYSICAL");
        room1.setOccupation("MEETING");

        RoomDTO room2 = new RoomDTO();
        room2.setId(2);
        room2.setType("PHYSICAL");
        room2.setOccupation("MEETING");

        RoomDTO room3 = new RoomDTO();
        room3.setId(3);
        room3.setType("PHYSICAL");
        room3.setOccupation("MEETING");

        Reservation r1 = new Reservation(1,1,1,1,1,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 07:00","01-17-2021 10:00");
        Reservation r2 = new Reservation(1,1,1,1,1,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 10:00","01-17-2021 11:00");
        Reservation r3 = new Reservation(1,1,1,1,2,RoomType.PHYSICAL,"Reserver",
                "01-18-2021 09:00","01-18-2021 11:00");
        Reservation r4 = new Reservation(1,1,1,1,2,RoomType.PHYSICAL,"Reserver",
                "01-18-2021 12:00","01-18-2021 13:00");


        Mockito.when(repository.findAllReservationsByRoomId(1)).thenReturn(Arrays.asList(r1, r2));
        Mockito.when(repository.findAllReservationsByRoomId(2)).thenReturn(Arrays.asList(r3,r4));

        RoomDTO[] allRooms = {room1, room2, room3};

        ResponseEntity<RoomDTO[]> resp = new ResponseEntity<>(allRooms,HttpStatus.OK);
        Mockito.when( restTemplate.getForEntity(
                ArgumentMatchers.anyString(),
                Mockito.eq( RoomDTO[].class ) ))
                .thenReturn(resp);

        List<RoomDTO> availableRooms = reservationService.getAllAvailableMeetingRooms( 1,"01-17-2021 12:30","01-17-2021 13:30" );

        assertEquals(3, availableRooms.toArray().length);

    }

    @Test
    public void viewAvailableRoomsByRoomTypeTestWrappedDatesConflict(){

        RoomDTO room1 = new RoomDTO();
        room1.setId(1);
        room1.setType("PHYSICAL");
        room1.setOccupation("MEETING");

        RoomDTO room2 = new RoomDTO();
        room2.setId(2);
        room2.setType("PHYSICAL");
        room2.setOccupation("MEETING");

        RoomDTO room3 = new RoomDTO();
        room3.setId(3);
        room3.setType("PHYSICAL");
        room3.setOccupation("MEETING");

        Reservation r1 = new Reservation(1,1,1,1,1,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 07:00","01-17-2021 10:00");
        Reservation r2 = new Reservation(1,1,1,1,1,RoomType.PHYSICAL,"Reserver",
                "01-17-2021 10:00","01-17-2021 11:00");
        Reservation r3 = new Reservation(1,1,1,1,2,RoomType.PHYSICAL,"Reserver",
                "01-18-2021 09:00","01-18-2021 11:00");
        Reservation r4 = new Reservation(1,1,1,1,2,RoomType.PHYSICAL,"Reserver",
                "01-18-2021 12:00","01-18-2021 13:00");


        Mockito.when(repository.findAllReservationsByRoomId(1)).thenReturn(Arrays.asList(r1, r2));
        Mockito.when(repository.findAllReservationsByRoomId(2)).thenReturn(Arrays.asList(r3,r4));

        RoomDTO[] allRooms = {room1, room2, room3};

        ResponseEntity<RoomDTO[]> resp = new ResponseEntity<>(allRooms,HttpStatus.OK);
        Mockito.when( restTemplate.getForEntity(
                ArgumentMatchers.anyString(),
                Mockito.eq( RoomDTO[].class ) ))
                .thenReturn(resp);

        List<RoomDTO> availableRooms = reservationService.getAllAvailableMeetingRooms( 1,"01-17-2021 06:30","01-17-2021 14:30" );

        assertEquals(2, availableRooms.toArray().length);

    }

    

}