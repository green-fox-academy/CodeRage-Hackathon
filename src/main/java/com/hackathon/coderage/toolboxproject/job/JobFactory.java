package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.dto.JobRequestDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.job.jobtypes.JobTypes;
import com.hackathon.coderage.toolboxproject.tool.Tool;

public class JobFactory {

  public static Job orderJob(String type, AppUser employee, Tool tool, JobRequestDTO request)
      throws IncorrectJobTypeException {
    try {
      return JobTypes.valueOf(type.toUpperCase()).create(employee, tool, request);
    } catch (IllegalArgumentException e) {
      throw new IncorrectJobTypeException();
    }
  }
}
