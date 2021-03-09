package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuildingDTO {
    private int buildingId;
    private String city;
    private String streetAddress;
    private LocationDTO location;
    private List<RoomDTO> rooms;
    private int totalFloors;
}
