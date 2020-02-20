package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.job.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobAttributeResponseDTO extends ResponseDTO {

  private String customer;
  private String type;
  private long startTime;
  private long endTime;
  private String status;

  public JobAttributeResponseDTO(Job job) {
    this.customer = job.getCustomer().getFullName();
    this.type = job.getType();
    this.startTime = job.getStartTime();
    this.endTime = job.getEndTime();
    this.status = job.getStatus();
  }
}
