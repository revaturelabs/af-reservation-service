package com.revature.reservationtracker.repotests;

import com.revature.entities.Reservation;
import com.revature.entities.Room;
import com.revature.repos.ReservationRepo;
import com.revature.repos.RoomRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ReservationRepoTest {
@Autowired
    ReservationRepo reservationRepo;
@Autowired
    RoomRepo roomRepo;

    @Test
    public void find_By_RoomId(){
       Set<Reservation> reservations= reservationRepo.findByRoomIdAndStatus(1,"reserved");
        //System.out.println(roomRepo.findAll());
        Assertions.assertNotEquals(0,reservations.size());

    }

    @Test
    public void find_Conflict(){

    }

}
