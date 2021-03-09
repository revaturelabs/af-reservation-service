package com.revature.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.revature.util.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDTO {
    private Integer reservationId;
    private Integer batchId;
    private Integer buildingId;
    private Integer locationId;
    private Integer roomId;
    private RoomType roomType;
    private String reserver;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm", timezone="EST")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm", timezone = "EST")
    private Date endDate;

    @Transient
    private final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm");
}
