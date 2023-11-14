package com.mirsal.backendmirsal.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRespoDTO {
    private String username;

    private String email;

    private String phoneNumber;

    @Builder.Default
    private boolean isActive=true;
}
