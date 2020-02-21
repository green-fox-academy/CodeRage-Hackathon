package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.job.Job;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobResponseDTO extends JobsResponseDTO {

  private long id;
  private String customerName;
  private String type;
  private String status;
  private long price;
  private Date createdAt;

  public JobResponseDTO(Job job) {
    this.id = job.getId();
    this.customerName = job.getCustomer().getFullName();
    this.type = job.getClass().getSimpleName();
    this.status = job.getStatus();
    this.price = job.getPrice();
  }
}
