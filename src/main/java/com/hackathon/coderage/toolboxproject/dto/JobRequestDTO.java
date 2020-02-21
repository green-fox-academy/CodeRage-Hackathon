package com.hackathon.coderage.toolboxproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobRequestDTO extends RequestDTO {

  private String type;
  private long date;
}
