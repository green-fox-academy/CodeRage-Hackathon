package com.hackathon.coderage.toolboxproject.job.jobtypes;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.job.Job;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Construction extends Job {

  public Construction(AppUser employee, Tool tool) {
    super(employee, tool);
  }
}
