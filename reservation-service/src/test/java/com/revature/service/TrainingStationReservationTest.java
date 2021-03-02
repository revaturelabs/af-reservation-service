package com.revature.service;

import com.revature.model.Building;
import com.revature.model.Reservation;
import com.revature.model.RoomType;
import com.revature.repository.ReservationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static com.revature.model.RoomType.VIRTUAL;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrainingStationReservationTest {


    private ReservationServiceImpl reservationService;

    private ReservationRepository repository;

    @BeforeClass
    public static void beforeClass() {

    }

    public Reservation reservation1 = new Reservation();
    public Reservation reservation2 = new Reservation();
    public Reservation meetingRoom = new Reservation();



    @Before
    public void before() {
        repository = Mockito.mock(ReservationRepository.class);
        reservationService = new ReservationServiceImpl(repository);
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
    public void addTrainingStation(){
        Reservation test = reservationService.addReservation(reservation1);
        Assert.assertEquals(reservation1, test);
    }

    @Test
    public void GetTrainingStationById(){
        Reservation getRes = reservationService.getReservationById(111);
        Assert.assertEquals(getRes, reservation1);
    }

    @Test
    public void getAllTrainingStations(){
        reservationService.addReservation(reservation1);
        reservationService.addReservation(reservation2);
        reservationService.addReservation(meetingRoom);
        Assert.assertEquals(2, reservationService.findTrainingStations().size());
    }

    @Test
    public void deleteTrainingStationById(){
        reservationService.addReservation(reservation1);
        reservationService.addReservation(reservation2);
        reservationService.addReservation(meetingRoom);
        Assert.assertEquals(2, reservationService.findTrainingStations().size());

        reservationService.deleteReservation(201);
        Assert.assertEquals(1, reservationService.findTrainingStations().size());
    }

    @Test
    public void updateTrainingStation(){
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
