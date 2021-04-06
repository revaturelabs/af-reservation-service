package com.revature.entities;

import javax.persistence.*;

/** Persisted bean for the reservation. Bean is managed by Spring. Mapped to Table reservation in the database */
@Entity
@Table(name = "reservation")
public class Reservation {

    /** Unique reservation id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name = "reservation_title")
    private String title;

    /** The email of the user reserving the room */
    @Column(name = "reserver")
    private String reserver;

    /** Start time of the reservation */
    @Column(name = "start_time")
    private long startTime;

    /** End time of the reservation */
    @Column(name = "end_time")
    private long endTime;

    /** Status of reservation: 'reserved' | 'canceled' | 'completed' */
    @Column(name = "reservation_status")
    private String status;

    /** Unique Id of the room */
    @JoinColumn(name = "room_id")
    @Column(name = "room_id")
    private int roomId;

    public Reservation() {
    }

    public Reservation(int reservationId, String reserver, long startTime, long endTime, String status, int roomId, String title) {
        this.reservationId = reservationId;
        this.reserver = reserver;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.roomId = roomId;
        this.title = title;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getReserver() {
        return reserver;
    }

    public void setReserver(String reserver) {
        this.reserver = reserver;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", reserver='" + reserver + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", roomId=" + roomId +
                ", title='" + title + '\'' +
                '}';
    }
}
