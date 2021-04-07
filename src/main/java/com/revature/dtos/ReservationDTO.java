package com.revature.dtos;

/**
 * Data transfer object that is not persisted and is only meant to take the input from the request body. Has all the same
 * fields and methods as the entity version
 */
public class ReservationDTO {

    private int reservationId;
    private String reserver;
    private long startTime;
    private long endTime;
    private String status;
    private int roomId;
    private String title;

    public ReservationDTO() {
    }

    public ReservationDTO(int reservationId, String reserver, long startTime, long endTime, String status, int roomId, String title) {
        this.reservationId = reservationId;
        this.reserver = reserver;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.roomId = roomId;
        this.title = title;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getTitle() {
        return title;
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
