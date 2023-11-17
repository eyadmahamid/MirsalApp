package com.mirsal.backendmirsal.model.dto;


import com.mirsal.backendmirsal.model.Entity.User;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class UpdateEventReqDTO {

    private Long event_id;
    private String location;
    private String occasion;
    private String description;

    @Builder.Default
    private List<User> managers;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
