package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.job.Job;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobResponseDTO extends ResponseDTO {

  private long id;
  private String customerName;
  private String type;
  private String status;
  private long price;
  private Set<ToolResponseDTO> tools;
  private Set<UserResponseDTO> employees;

  public JobResponseDTO(Job job) {
    this.id = job.getId();
    this.customerName = job.getCustomer().getFullName();
    this.type = job.getClass().getSimpleName();
    this.status = job.getStatus();
    this.price = job.getPrice();
    this.tools = job.getTools()
        .stream()
        .map(ToolResponseDTO::new)
        .collect(Collectors.toSet());
    this.employees = job.getEmployees()
        .stream()
        .map(UserResponseDTO::new)
        .collect(Collectors.toSet());
  }
}
