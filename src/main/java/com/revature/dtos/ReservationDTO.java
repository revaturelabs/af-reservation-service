package com.revature.dtos;

public class ReservationDTO {

    private int reservationId;
    private String reserver;
    private long startTime;
    private long endTime;
    private String status;
    private int roomId;

    public ReservationDTO() {
    }

    public ReservationDTO(int reservationId, String reserver, long startTime, long endTime, String status, int roomId) {
        this.reservationId = reservationId;
        this.reserver = reserver;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.roomId = roomId;
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

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationId=" + reservationId +
                ", reserver='" + reserver + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}
