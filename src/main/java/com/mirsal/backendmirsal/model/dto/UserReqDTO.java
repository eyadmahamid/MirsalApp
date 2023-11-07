package com.mirsal.backendmirsal.model.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReqDTO {

    private String username;

    @Email
    private String email;

    private String phoneNumber;

    private String password;
}
