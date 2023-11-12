package com.mirsal.backendmirsal.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class UpdateEventReqDTO {
    private Long event_id;
    private String occasion;
    private String description;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
