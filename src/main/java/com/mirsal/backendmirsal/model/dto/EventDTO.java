package com.mirsal.backendmirsal.model.dto;

import com.mirsal.backendmirsal.model.Entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDTO {


    private Long id;

    private User organizer_id;

    private String location;

    private String occasion;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
