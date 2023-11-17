package com.mirsal.backendmirsal.model.dto;

import com.mirsal.backendmirsal.model.Entity.User;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class EventManagerDTO extends User {

    private Long user_id;

    private String username;

    @Email
    private String email;


    private String phoneNumber;
}
