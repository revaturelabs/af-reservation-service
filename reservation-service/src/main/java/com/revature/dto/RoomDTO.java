package com.revature.dto;

import com.revature.model.Building;
import com.revature.model.RoomOccupation;
import com.revature.model.RoomType;
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
    private int roomId;
    private String name;
    private RoomType type;
    private RoomOccupation occupation;
    private int capacity;
    //comment it because location server did not have building in their RoomDTO
    //private Building building;
    private Map<String, Integer> roomAmenities;
    private int floorNumber;

}
