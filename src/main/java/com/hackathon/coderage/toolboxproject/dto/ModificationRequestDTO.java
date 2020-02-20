package com.hackathon.coderage.toolboxproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModificationRequestDTO extends RequestDTO {

  private String role;
  private long id;
}
