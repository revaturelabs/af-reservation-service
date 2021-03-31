package com.revature.dtos;

public class RoomDTO {

    private int roomId;
    private String name;
    private String type;
    private int buildingId;
    private int capacity;

    public RoomDTO(int roomId, String name, String type, int buildingId, int capacity) {
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.buildingId = buildingId;
        this.capacity = capacity;
    }

    public RoomDTO() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomId=" + roomId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", buildingId=" + buildingId +
                ", capacity=" + capacity +
                '}';
    }
}
