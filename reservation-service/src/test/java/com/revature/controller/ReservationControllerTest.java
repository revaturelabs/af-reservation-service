package com.revature.controller;

import com.revature.model.Reservation;
import com.revature.service.ReservationServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ReservationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ReservationServiceImpl reservationService;

    @Test
    public void getTrainingStations() throws Exception {
        List<Reservation> trainingStations = new ArrayList<>();
        trainingStations.add(new Reservation());
        trainingStations.add(new Reservation());
        doReturn(trainingStations).when(reservationService).getTrainingStationReservations();

        mockMvc.perform(MockMvcRequestBuilders.get("trainingstations")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }
}
