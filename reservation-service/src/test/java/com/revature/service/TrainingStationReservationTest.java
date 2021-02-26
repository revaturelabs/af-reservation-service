package com.revature.service;

import com.revature.model.Building;
import com.revature.model.Reservation;
import com.revature.model.RoomType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static com.revature.model.RoomType.VIRTUAL;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrainingStationReservationTest {

    @InjectMocks
    private ReservationService reservationService;

    @BeforeClass
    public static void beforeClass() {
        Building b = new Building();
        Map<String, Integer> amenities = new HashMap<>();
    }

    Reservation reservation1;
    Reservation reservation2;
    Reservation meetingRoom;


    @Before
    void before() {
        reservation1.setReservationId(111);
        reservation1.setRoomType(VIRTUAL);
        reservation1.setReserver("Revature CEO");
        reservation1.setBuildingId(101);
        reservation1.setRoomId(101);

        reservation2.setReservationId(112);
        reservation2.setRoomType(VIRTUAL);
        reservation2.setReserver("Revature CEO");
        reservation2.setBuildingId(101);
        reservation2.setRoomId(201);

        meetingRoom.setReservationId(113);
        meetingRoom.setRoomType(RoomType.PHYSICAL);
        meetingRoom.setReserver("Revature CEO");
        meetingRoom.setBuildingId(101);
        meetingRoom.setRoomId(303);

    }


    @Test
    void addTrainingStation(){
        Reservation test = reservationService.addReservation(reservation1);
        Assert.assertEquals(test,reservation1);
    }

    @Test
    void GetTrainingStationById(){
        Reservation getRes = reservationService.getReservationById(111);
        Assert.assertEquals(getRes, reservation1);
    }

    @Test
    void getAllTrainingStations(){
        reservationService.addReservation(reservation1);
        reservationService.addReservation(reservation2);
        reservationService.addReservation(meetingRoom);
        Assert.assertEquals(2, reservationService.getTrainingStationReservations().size());
    }

    @Test
    void deleteTrainingStationById(){
        reservationService.addReservation(reservation1);
        reservationService.addReservation(reservation2);
        reservationService.addReservation(meetingRoom);
        Assert.assertEquals(2, reservationService.getTrainingStationReservations().size());

        reservationService.deleteReservation(201);
        Assert.assertEquals(1, reservationService.getTrainingStationReservations().size());
    }

    @Test
    void updateTrainingStation(){
        reservationService.addReservation(reservation1);
        reservationService.addReservation(reservation2);
        Reservation test = reservationService.addReservation(reservation1);
        Assert.assertEquals(reservation1, reservationService.getReservationById(111));

        reservation1.setReserver("new reserver");
        reservationService.updateReservation(reservation1);
        Reservation updatedRes = reservationService.getReservationById(111);
        Assert.assertEquals(reservation1.getReserver(), updatedRes.getReserver());
    }
}
