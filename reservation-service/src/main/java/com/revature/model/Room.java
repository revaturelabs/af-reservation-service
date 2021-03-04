package com.revature.model;

import java.util.Map;

public class Room {
    //copy the room fields from location team later
    private int id;
    private String name;
    private RoomType type;
    private RoomOccupation occupation;
    private int capacity;
    private Building building;
    private Map<String, Integer> roomAmenities;
    private int floorNumber;

    public Room(int id, String name, RoomType type, RoomOccupation occupation, int capacity, Building building, Map<String, Integer> roomAmenities, int floorNumber) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.occupation = occupation;
        this.capacity = capacity;
        this.building = building;
        this.roomAmenities = roomAmenities;
        this.floorNumber = floorNumber;
    }

    public Room(int id, String name, RoomType type, RoomOccupation occupation, int capacity, Building building) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.occupation = occupation;
        this.capacity = capacity;
        this.building = building;
    }

    public Room(int i) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public RoomOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(RoomOccupation occupation) {
        this.occupation = occupation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Map<String, Integer> getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(Map<String, Integer> roomAmenities) {
        this.roomAmenities = roomAmenities;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
