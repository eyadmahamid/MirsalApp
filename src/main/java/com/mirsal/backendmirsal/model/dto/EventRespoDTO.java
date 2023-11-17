package com.mirsal.backendmirsal.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder

public class EventRespoDTO {

    private Long event_id;

    private Long organizer_id;

    private String occasion;

    private String location;

    private Date date;

    private String description;

}
