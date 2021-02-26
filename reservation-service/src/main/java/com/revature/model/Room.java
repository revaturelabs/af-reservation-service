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
}
