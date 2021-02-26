package com.revature.dataLooader;

import com.revature.model.Reservation;
import com.revature.model.RoomType;
import com.revature.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class BootstrapData implements CommandLineRunner {


    private final ReservationRepository repository;

    public BootstrapData(ReservationRepository repository) {
        this.repository = repository;
    }

    @Value("${my.value}")
    String myValue;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Environment Running: " + myValue);

        Reservation reservation1 = new Reservation(
                9,1,1,1,1, RoomType.PHYSICAL,"Revature CEO",
                "01-17-2021 09:00","01-17-2021 17:00");
        Reservation reservation2 = new Reservation(
                6,1,1,1,1,RoomType.PHYSICAL,"Revature CEO",
                "01-18-2021 09:00","01-18-2021 17:00");
        Reservation reservation3 = new Reservation(
                8,1,1,1,1,RoomType.PHYSICAL,"Revature CEO",
                "01-19-2021 09:00","01-19-2021 17:00");

        List<Reservation> reservationList = new ArrayList<>(Arrays.asList(reservation1, reservation2,reservation3));

        repository.saveAll(reservationList);
    }
}
