package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToolResponseDTO extends ResponseDTO {

  private String name;
  private int hourlyPrice;
  private List<JobAttributeResponseDTO> jobs = new ArrayList<>();

  public ToolResponseDTO(Tool tool) {
    this.name = tool.getName();
    this.hourlyPrice = tool.getHourlyPrice();
    this.jobs = tool.getJobs().stream()
        .map(JobAttributeResponseDTO::new)
        .collect(Collectors.toList());
  }
}
