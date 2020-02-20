package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.job.Job;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobsResponseDTO extends ResponseDTO {

  private List<Job> jobs;
}
