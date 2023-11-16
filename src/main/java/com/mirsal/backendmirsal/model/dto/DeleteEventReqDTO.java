package com.mirsal.backendmirsal.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteEventReqDTO {

    private Long userId;
    private Long eventId;

}
