package com.revature.reservationtracker.controllerTests;

import com.revature.dtos.UserDTO;
import com.revature.entities.Reservation;
import com.revature.services.ReservationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.SetEnvironmentVariable;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.reservationtracker.ReservationtrackerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {"spring.cloud.discovery.enabled=false", "spring.cloud.consul.enabled=false", "spring.cloud.config.enabled=false"})
@SetEnvironmentVariable(key = "AUTH_SERVER", value = "http://35.232.107.40:8080/verify")
public class ReservationControllerTest {

    @MockBean
    ReservationService reservationService;

    @Autowired
    MockMvc mvc;

    static UserDTO mockUser;
    static String jwt;

    @BeforeAll
    static void setUp() throws Exception {
        jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJpZCI6MjMsImVtYWlsIjoibGNhcnJpY284MjdAZ21haWwuY29tIn0.lrI1-a3CfLb-nVeHZ9BJBHJ1MN2RHezl8DyP8J4GM8A";
        mockUser = new UserDTO(1, "email@revature.com", "trainer");
    }

    @Test
    void create_reservation() throws Exception {
        String json = "{\"roomId\":\"1\", \"name\":\"John Doe\", \"type\":\"meeting\", \"buildingId\":0, \"capacity\":\"5\"}";
        when(reservationService.createReservation(any())).thenReturn(new Reservation());

        mvc.perform(MockMvcRequestBuilders
                .post("/reservations")
                .header("Authorization",jwt)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void get_reservations() throws Exception {
        Mockito.when(reservationService.getCustomReservations(anyInt(), any(), any(), any(), any())).thenReturn(new HashSet<>());

        mvc.perform(MockMvcRequestBuilders
                .get("/reservations?roomId=1")
                .header("Authorization",jwt)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void get_reservation() throws Exception {
        when(reservationService.getReservationById(anyInt())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .get("/reservations/2")
                .header("Authorization",jwt)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void cancel_reservations() throws Exception {
        String json = "{\"roomId\":\"1\", \"name\":\"John Doe\", \"type\":\"meeting\", \"buildingId\":0, \"capacity\":\"5\"}";
        when(reservationService.cancelReservation(anyInt(), any())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .patch("/reservations/2?action='cancel'")
                .header("Authorization",jwt)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void update_reservations() throws Exception {
        String json = "{\"roomId\":\"1\", \"name\":\"John Doe\", \"type\":\"meeting\", \"buildingId\":0, \"capacity\":\"5\"}";
        Mockito.when(reservationService.updateReservation(any(), any())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .patch("/reservations/2")
                .header("Authorization",jwt)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
