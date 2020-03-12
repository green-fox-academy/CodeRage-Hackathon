package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.tool.Tool;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToolResponseDTO extends ResponseDTO {

  private String name;
  private int dailyPrice;

  public ToolResponseDTO(Tool tool) {
    this.name = tool.getName();
    this.dailyPrice = tool.getDailyPrice();
  }
}
