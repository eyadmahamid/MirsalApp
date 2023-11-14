package com.mirsal.backendmirsal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder

public class EventRespoDTO {

    @JsonProperty("organizer_id")
    private String organizer_id;

    @JsonProperty("occasion")
    private String occasion;

    @JsonProperty("location")
    private String location;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("description")
    private String description;
}
