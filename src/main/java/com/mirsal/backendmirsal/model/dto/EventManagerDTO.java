package com.mirsal.backendmirsal.model.dto;

import com.mirsal.backendmirsal.model.Entity.User;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class EventManagerDTO {
    private Long user_id;

    private String username;

    @Email
    private String email;

    private String phoneNumber;
}
