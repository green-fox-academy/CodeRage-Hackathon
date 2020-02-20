package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.job.jobtypes.JobTypes;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.List;

public class JobFactory {

  public static Job orderJob(String type, List<AppUser> employees, List<Tool> tools)
      throws IncorrectJobTypeException {
    try {
      return JobTypes.valueOf(type.toUpperCase()).create(employees, tools);
    } catch (IllegalArgumentException e) {
      throw new IncorrectJobTypeException();
    }
  }
}
