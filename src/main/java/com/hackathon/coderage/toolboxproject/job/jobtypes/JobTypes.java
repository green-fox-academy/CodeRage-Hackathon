package com.hackathon.coderage.toolboxproject.job.jobtypes;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.dto.JobRequestDTO;
import com.hackathon.coderage.toolboxproject.job.Job;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.Set;

public enum JobTypes {

  CARPENTER {
    @Override
    public Job create(Set<AppUser> employees, Tool tool, JobRequestDTO request) {
      return new Carpenter(employees, tool, request);
    }
  },

  CONSTRUCTION {
    @Override
    public Job create(Set<AppUser> employees, Tool tools, JobRequestDTO request) {
      return new Construction(employees, tools, request);
    }
  };

  public abstract Job create(Set<AppUser> employees, Tool tools, JobRequestDTO request);
}
