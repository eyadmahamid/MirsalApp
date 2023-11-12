package com.mirsal.backendmirsal.model.dto;

<<<<<<< HEAD
=======

>>>>>>> acb1aa0d1240c7d640088b9bead09c544b14d139
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class UpdateEventReqDTO {
<<<<<<< HEAD
    private Long event_id;
    private String occasion;
    private String description;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
=======

    private Long event_id;

    private Long organizer_id;

    private String occasion;

    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

>>>>>>> acb1aa0d1240c7d640088b9bead09c544b14d139
}
