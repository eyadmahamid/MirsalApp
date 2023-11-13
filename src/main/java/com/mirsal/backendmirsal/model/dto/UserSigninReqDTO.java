package com.mirsal.backendmirsal.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSigninReqDTO {


//    @NotNull(message = "Please enter a valid username")
    // If this username is already taken? = "Invalid username. Try again!")
//    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can contain only letters and digits")
    private String username;


//    @NotNull(message = "Please enter your email")
    // If this email is already used
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Please enter a valid password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Builder.Default
    private boolean isActive=false;

    public String getEmailOrUsername(){

        return email !=null  ? email : username;
    }
}
