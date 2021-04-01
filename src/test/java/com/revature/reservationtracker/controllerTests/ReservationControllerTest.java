package com.revature.reservationtracker.controllerTests;

import com.revature.aspects.SecurityAspect;
import com.revature.entities.Reservation;
import com.revature.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.revature.reservationtracker.ReservationtrackerApplication.class)
public class ReservationControllerTest {

    @MockBean
    ReservationService reservationService;

    @MockBean
    SecurityAspect securityAspect;

    @Autowired
    MockMvc mvc;

    @Test
    void create_reservation() throws Exception{
        String json = "{\"roomId\":\"1\", \"name\":\"John Doe\", \"type\":\"meeting\", \"buildingId\":0, \"capacity\":\"5\"}";
        Mockito.when(reservationService.createReservation(any())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .post("/rooms/1/reservations")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void get_reservations() throws Exception{
        Mockito.when(reservationService.getActiveReservationsByRoomId(anyInt())).thenReturn(new HashSet<>());
        mvc.perform(MockMvcRequestBuilders
                .get("/rooms/1/reservations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void get_reservation() throws Exception{
        Mockito.when(reservationService.getReservationById(anyInt())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .get("/rooms/1/reservations/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void cancel_reservations() throws Exception{
        String json = "{\"roomId\":\"1\", \"name\":\"John Doe\", \"type\":\"meeting\", \"buildingId\":0, \"capacity\":\"5\"}";
        Mockito.when(reservationService.cancelReservation(anyInt(), any())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .patch("/rooms/1/reservations/2?action='cancel'")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void update_reservations() throws Exception{
        String json = "{\"roomId\":\"1\", \"name\":\"John Doe\", \"type\":\"meeting\", \"buildingId\":0, \"capacity\":\"5\"}";
        Mockito.when(reservationService.updateReservationTime(any(), any())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .patch("/rooms/1/reservations/2")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
