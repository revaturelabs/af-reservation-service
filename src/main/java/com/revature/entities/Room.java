package com.revature.entities;

import javax.persistence.*;

/**
 * Persisted bean for the room. Bean is managed by Spring. Mapped to Table room in the database
 */
@Entity
@Table(name = "room")
public class Room {

    /**
     * Unique room id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    /**
     * Name of the room
     */
    @Column(name = "room_name")
    private String name;

    /**
     * Type of room: 'meeting' | 'classroom' | 'virtual'
     */
    @Column(name = "room_type")
    private String type;

    /**
     * Unique building id
     */
    @JoinColumn(name = "building_id")
    @Column(name = "bld_id")
    private int buildingId;

    /**
     * max room occupancy
     */
    @Column(name = "capacity")
    private int capacity;

    public Room() {
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
