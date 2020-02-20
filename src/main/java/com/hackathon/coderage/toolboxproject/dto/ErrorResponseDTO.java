package com.hackathon.coderage.toolboxproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDTO extends ResponseDTO {

  private String status;
  private String message;
}
