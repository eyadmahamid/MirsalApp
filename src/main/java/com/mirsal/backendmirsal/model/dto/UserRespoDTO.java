package com.mirsal.backendmirsal.model.dto;

import lombok.Builder;

@Builder
public class UserRespoDTO {

    private String username;
    private String email;
    private String phoneNumber;
    @Builder.Default
    private boolean isActive=true;


}
