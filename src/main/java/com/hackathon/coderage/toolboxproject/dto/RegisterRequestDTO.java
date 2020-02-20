package com.hackathon.coderage.toolboxproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO extends RequestDTO {

  private String username;
  private String password;
  private String fullName;
  private String qualification;
}
