package com.mirsal.backendmirsal.model.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserToEventManagerDTO {


  private Long user_id;

  private String email;

  private String username;

  private String phoneNumber;

}
