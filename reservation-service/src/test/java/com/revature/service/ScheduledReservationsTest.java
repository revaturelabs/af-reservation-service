package com.revature.service;

import com.revature.model.Reservation;
import com.revature.model.RoomType;
import com.revature.repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledReservationsTest {

    private ReservationService service;
    private ReservationRepository repository;

    @Before
    public void preTest(){
        repository = Mockito.mock(ReservationRepository.class);
        service = new ReservationServiceImpl(repository);
    }

    @Test
    public void scheduledReservationsByRoomTest() {

        Reservation reservation1 = new Reservation();
        reservation1.setReservationId(1);
        reservation1.setRoomType(RoomType.PHYSICAL);
        reservation1.setReserver("Revature CEO");
        reservation1.setBuildingId(1);
        reservation1.setRoomId(2);

        Reservation reservation2 = new Reservation();
        reservation2.setReservationId(2);
        reservation2.setRoomType(RoomType.PHYSICAL);
        reservation2.setReserver("Revature CEO");
        reservation2.setBuildingId(1);
        reservation2.setRoomId(2);

        Reservation reservation3 = new Reservation();
        reservation3.setReservationId(3);
        reservation3.setRoomType(RoomType.PHYSICAL);
        reservation3.setReserver("Revature CEO");
        reservation3.setBuildingId(1);
        reservation3.setRoomId(2);

        List<Reservation> reservationList =
                new ArrayList<>(Arrays.asList(reservation1, reservation2,reservation3));

        Mockito.when(repository.findAllReservationsByRoomId(anyInt()))
                .thenReturn(reservationList);

        List<Reservation> allReservations = service.getAllReservationsByRoomId(2);

        assertNotNull(allReservations);
        assertEquals(3,allReservations.size());
        assertEquals("Revature CEO", allReservations.get(0).getReserver());
        assertEquals(Integer.valueOf(1), allReservations.get(0).getBuildingId());
        assertEquals(Integer.valueOf(2),allReservations.get(0).getRoomId());
        assertEquals(RoomType.PHYSICAL,allReservations.get(0).getRoomType());
        verify(repository,times(1)).findAllReservationsByRoomId(anyInt());
    }

}
