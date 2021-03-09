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
public class LocationDTO {
    private int locationId;
    private String state;
    private String city;
    private String zipCode;
    private List<BuildingDTO> buildings;

}
