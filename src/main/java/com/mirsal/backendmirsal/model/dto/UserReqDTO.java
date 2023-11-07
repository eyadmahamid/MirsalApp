package com.mirsal.backendmirsal.model.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReqDTO {

    @NotNull(message = "Please enter a valid username")
    // If this username is already taken? = "Invalid username. Try again!")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can contain only letters and digits")
    private String username;

    @NotNull(message = "Please enter your email")
    // If this email is already used
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Please enter a valid password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Required: Please enter your phone number")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number format. Please provide a 10-digit phone number.")
    private String phoneNumber;
}
