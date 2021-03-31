package com.revature.entities;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    @Column(name = "room_name")
    private String name;

    @Column(name = "room_type")
    private String type;

    @JoinColumn(name = "building_id")
    @Column(name = "building_id")
    private int buildingId;

    @Column(name = "room_capacity")
    private int capacity;

    public Room() {
    }

    public Room(int roomId, String name, String type, int buildingId, int capacity) {
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.buildingId = buildingId;
        this.capacity = capacity;
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
        return "Room{" +
                "roomId=" + roomId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", buildingId=" + buildingId +
                ", capacity=" + capacity +
                '}';
    }
}
