package com.revature.dto;
import com.revature.util.RoomOccupation;
import com.revature.util.RoomType;

public class RoomDTO {
    private int id;
    private String type;
    private String occupation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }

    //    private RoomType type;
//    private RoomOccupation occupation;
//    private int capacity;
//
//    private int floorNumber;

}