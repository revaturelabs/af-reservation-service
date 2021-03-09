package com.revature.dto;
import com.revature.util.RoomOccupation;
import com.revature.util.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDTO {
    private int id;
    private String type;
    private String occupation;
//    private RoomType type;
//    private RoomOccupation occupation;
//    private int capacity;
//
//    private int floorNumber;

}