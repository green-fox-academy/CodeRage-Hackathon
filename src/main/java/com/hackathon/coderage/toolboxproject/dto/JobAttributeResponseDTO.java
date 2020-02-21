package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.job.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobAttributeResponseDTO extends ResponseDTO {

  private String customer;
  private String type;
  private String status;

  public JobAttributeResponseDTO(Job job) {
    this.customer = job.getCustomer().getFullName();
    this.type = job.getType();
    this.status = job.getStatus();
  }
}
