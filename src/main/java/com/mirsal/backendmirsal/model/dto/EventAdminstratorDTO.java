package com.mirsal.backendmirsal.model.dto;

import com.mirsal.backendmirsal.model.Entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EventAdminstratorDTO {


    private Long userId;
    private List<EventRespoDTO> events;




    }
