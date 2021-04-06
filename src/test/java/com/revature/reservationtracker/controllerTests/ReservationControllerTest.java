package com.revature.reservationtracker.controllerTests;

import com.revature.dtos.UserDTO;
import com.revature.entities.Reservation;
import com.revature.services.ReservationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
public class ReservationControllerTest {

    @MockBean
    ReservationService reservationService;

    @Autowired
    MockMvc mvc;

    static UserDTO mockUser;
    static String jwt;

    protected static void setEnv(Map<String, String> newenv) throws Exception {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newenv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newenv);
        } catch (NoSuchFieldException e) {
            Class[] classes = Collections.class.getDeclaredClasses();
            Map<String, String> env = System.getenv();
            for(Class cl : classes) {
                if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                    Field field = cl.getDeclaredField("m");
                    field.setAccessible(true);
                    Object obj = field.get(env);
                    Map<String, String> map = (Map<String, String>) obj;
                    map.clear();
                    map.putAll(newenv);
                }
            }
        }
    }

    @BeforeAll
    static void setUp() throws Exception {
        Map<String, String> authserver = new HashMap<>();
        authserver.put("AUTH_SERVER", "http://35.232.107.40:8080");
        setEnv(authserver);
        jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJpZCI6MjMsImVtYWlsIjoibGNhcnJpY284MjdAZ21haWwuY29tIn0.lrI1-a3CfLb-nVeHZ9BJBHJ1MN2RHezl8DyP8J4GM8A";
        mockUser = new UserDTO(1, "email@revature.com", "trainer");
    }

    @Test
    void create_reservation() throws Exception {
        String json = "{\"roomId\":\"1\", \"name\":\"John Doe\", \"type\":\"meeting\", \"buildingId\":0, \"capacity\":\"5\"}";
        when(reservationService.createReservation(any())).thenReturn(new Reservation());

        mvc.perform(MockMvcRequestBuilders
                .post("/rooms/1/reservations")
                .header("Authorization",jwt)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void get_reservations() throws Exception {
        Mockito.when(reservationService.getActiveReservationsByRoomId(anyInt(), any(), any())).thenReturn(new HashSet<>());

        mvc.perform(MockMvcRequestBuilders
                .get("/rooms/1/reservations")
                .header("Authorization",jwt)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void get_reservation() throws Exception {
        when(reservationService.getReservationById(anyInt())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .get("/rooms/1/reservations/2")
                .header("Authorization",jwt)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void cancel_reservations() throws Exception {
        String json = "{\"roomId\":\"1\", \"name\":\"John Doe\", \"type\":\"meeting\", \"buildingId\":0, \"capacity\":\"5\"}";
        when(reservationService.cancelReservation(anyInt(), any())).thenReturn(new Reservation());
        mvc.perform(MockMvcRequestBuilders
                .patch("/rooms/1/reservations/2?action='cancel'")
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
                .patch("/rooms/1/reservations/2")
                .header("Authorization",jwt)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
