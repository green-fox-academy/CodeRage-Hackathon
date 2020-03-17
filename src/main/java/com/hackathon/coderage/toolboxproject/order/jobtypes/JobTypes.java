package com.hackathon.coderage.toolboxproject.order.jobtypes;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.dto.OrderRequestDTO;
import com.hackathon.coderage.toolboxproject.order.Order;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.Set;

public enum JobTypes {

  CARPENTER {
    @Override
    public Order create(Set<AppUser> employees, Set<Tool> tools, OrderRequestDTO request) {
      return new Carpenter(employees, tools, request);
    }
  },

  CONSTRUCTION {
    @Override
    public Order create(Set<AppUser> employees, Set<Tool> tools, OrderRequestDTO request) {
      return new Construction(employees, tools, request);
    }
  };

  public abstract Order create(Set<AppUser> employees, Set<Tool> tools, OrderRequestDTO request);
}
