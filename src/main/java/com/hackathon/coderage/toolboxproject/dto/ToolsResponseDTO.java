package com.hackathon.coderage.toolboxproject.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToolsResponseDTO {

  private List<ToolResponseDTO> tools;
}
