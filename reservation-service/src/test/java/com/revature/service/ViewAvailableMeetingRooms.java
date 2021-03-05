package com.revature.service;

import com.revature.dto.RoomDTO;
import com.revature.model.Reservation;
import com.revature.model.Room;
import com.revature.model.RoomType;
import com.revature.repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ViewAvailableMeetingRooms {

    private Reservation reservation;

    @MockBean
    private ReservationRepository repository;

    @MockBean
    private RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    private ReservationServiceImpl reservationService;

    private RoomDTO rooms;

    @Before
    public void setUp() throws Exception {

        reservation = new Reservation( 1, 1, 1, 1,
                RoomType.PHYSICAL,
                "JUnit Test",
                "02-02-2021 00:00",
                "02-02-2021 01:00" );

        reservationService.setRestTemplate(restTemplate);
        //batch = new BatchDTO(101, "TR-1201", "Mock Batch 101", "2021-12-03","2022-02-11");
    }

    @Test
    public void viewAvailableRoomsByRoomTypeTest(){

//        Reservation expected = new Reservation( 1, 101, 1, 1, 1,
//                RoomType.PHYSICAL,
//                "JUnit Test",
//                "02-02-2021 00:00",
//                "02-02-2021 01:00" );
//
//        String message = "Test to try to add a batch to the reservation when the reservation " +
//                "already has a batch";
//
//        Mockito.when( restTemplate.getForObject( Mockito.anyString(),
//                Mockito.eq(RoomDTO.class) ))
//                .thenReturn( rooms );
//
//        reservationService.assignBatch( reservation.getReservationId(), 101);
//
//        assertEquals(message, expected, savedReservation.getValue());
    }

}