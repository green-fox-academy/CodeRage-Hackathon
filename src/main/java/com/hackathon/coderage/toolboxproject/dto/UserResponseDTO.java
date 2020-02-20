package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDTO extends ResponseDTO {

  private String username;
  private String role;

  public UserResponseDTO(AppUser user) {
    this.username = user.getUsername();
    this.role = user.getRole();
  }
}
