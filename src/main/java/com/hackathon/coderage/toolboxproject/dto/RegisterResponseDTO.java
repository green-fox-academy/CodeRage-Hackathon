package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO extends ResponseDTO {

  private long id;
  private String username;
  private String fullName;

  public RegisterResponseDTO(AppUser appUser) {
    this.id = appUser.getId();
    this.username = appUser.getUsername();
    this.fullName = appUser.getFullName();
  }
}
