package com.hackathon.coderage.toolboxproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO extends ResponseDTO {

  String jwt;
}
