package com.hackathon.coderage.toolboxproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobTypeRequestDTO extends ResponseDTO {

  private String name;
  private long crewSize;
  private String requiredTools;
  private String requiredQualification;
}
