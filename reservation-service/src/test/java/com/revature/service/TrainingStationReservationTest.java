package com.revature.service;

import com.revature.model.Building;
import com.revature.model.Reservation;
import com.revature.model.Room;
import com.revature.repository.ReservationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.revature.model.RoomOccupation.TRAINING;
import static com.revature.model.RoomType.VIRTUAL;

@SpringBootTest
public class TrainingStationReservationTest {

    @MockBean
    private ReservationService reservationService;

    Building b;
    Map<String, Integer> amenities;
    Room trainingStation1;
    Room trainingStation2;
    Room meetingRoom;

    @BeforeClass
    public static void beforeClass() {
        Building b = new Building();
        Map<String, Integer> amenities = new HashMap<>();
    }


    @Before
    public static void before() {
        Building b = new Building();
        Map<String, Integer> amenities = new HashMap<>();
        Room trainingStation1 = new Room(101,"ts1",VIRTUAL,TRAINING,50, b);
        Room trainingStation2 = new Room(201,"ts1",VIRTUAL,TRAINING,50, b);
        Room meetingRoom = new Room(111,"ts1",VIRTUAL,TRAINING,50, b, amenities,1);

    }


    @Test
    void addTrainingStation(){
        Reservation reservation1 = reservationService.save(trainingStation1);
        Assert.assertEquals(reservation1,trainingStation1);
    }

    @Test
    void GetTrainingStationById(){
        Reservation reservation1 = reservationService.save(trainingStation1);
        Assert.assertEquals(trainingStation1, reservationService.findTSById(101));
    }

    @Test
    void getAllTrainingStations(){
        reservationService.save(trainingStation1);
        reservationService.save(trainingStation2);
        reservationService.save(meetingRoom);
        Assert.assertArrayEquals(2, reservationService.findAllTS().size());
    }

    @Test
    void deleteTrainingStationById(){
        reservationService.save(trainingStation1);
        reservationService.save(trainingStation2);
        reservationService.save(meetingRoom);
        Assert.assertArrayEquals(2, reservationService.findAllTS().size());

        reservationService.deleteTSById(201);
        Assert.assertArrayEquals(1, reservationService.findAllTS().size());
    }

    @Test
    void updateTrainingStation(){
        reservationService.save(trainingStation1);
        reservationService.save(trainingStation2);
        Reservation reservation1 = reservationService.save(trainingStation1);
        Assert.assertEquals(reservation1, reservationService.findTSById(101));

        reservation1.setReserver("new reserver");
        Assert.assertEquals(reservation1.getReserver(), reservationService.updateTS(reservation1));
    }
}
