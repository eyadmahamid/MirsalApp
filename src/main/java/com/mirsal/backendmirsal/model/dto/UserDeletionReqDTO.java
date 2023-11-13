package com.mirsal.backendmirsal.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class UserDeletionReqDTO {

    @NotNull(message = "Please enter a valid password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}
