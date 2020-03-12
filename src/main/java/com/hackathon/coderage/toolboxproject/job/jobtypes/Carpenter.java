package com.hackathon.coderage.toolboxproject.job.jobtypes;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.dto.JobRequestDTO;
import com.hackathon.coderage.toolboxproject.job.Job;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.Set;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Carpenter extends Job {

  public Carpenter(Set<AppUser> employees, Set<Tool> tools, JobRequestDTO request) {
    super(employees, tools, request);
  }
}
