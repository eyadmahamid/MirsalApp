package com.mirsal.backendmirsal.model.dto;

<<<<<<< HEAD
public class DeleteEventReqDTO {

    private Long user_id;
=======
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteEventReqDTO {

    private Long userId;

    private Long eventId;
>>>>>>> acb1aa0d1240c7d640088b9bead09c544b14d139
}
