package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.jobtype.JobType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobTypeResponseDTO extends ResponseDTO {

  private String name;
  private long crewSize;
  private String requiredTools;
  private String requiredQualification;

  public JobTypeResponseDTO(JobType jobType) {
    this.name = jobType.getName();
    this.crewSize = jobType.getCrewSize();
    this.requiredTools = jobType.getRequiredTools();
    this.requiredQualification = jobType.getRequiredQualification();
  }
}
