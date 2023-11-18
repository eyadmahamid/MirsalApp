package com.mirsal.backendmirsal.model.dto;

import com.mirsal.backendmirsal.model.Entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class EventDTO {


    private Long event_id;

    private Long organizer_id;

    @Builder.Default
    private List<EventManagerDTO> managers = new ArrayList<>();

    private String location;

    private String occasion;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
