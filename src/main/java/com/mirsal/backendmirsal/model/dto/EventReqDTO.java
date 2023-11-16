package com.mirsal.backendmirsal.model.dto;

import com.mirsal.backendmirsal.model.Entity.User;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class EventReqDTO {

    private String occasion;

    private String location;

    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
}
