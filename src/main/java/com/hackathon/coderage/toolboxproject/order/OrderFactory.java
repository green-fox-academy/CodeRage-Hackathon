package com.hackathon.coderage.toolboxproject.order;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.dto.OrderRequestDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.order.jobtypes.JobTypes;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.Set;

public class OrderFactory {

  public static Order orderJob(String type, Set<AppUser> employees, Set<Tool> tools,
      OrderRequestDTO request)
      throws IncorrectJobTypeException {
    try {
      return JobTypes.valueOf(type.toUpperCase()).create(employees, tools, request);
    } catch (IllegalArgumentException e) {
      throw new IncorrectJobTypeException();
    }
  }
}
