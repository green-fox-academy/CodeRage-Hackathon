package com.hackathon.coderage.toolboxproject.order.jobtypes;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.dto.OrderRequestDTO;
import com.hackathon.coderage.toolboxproject.order.Order;
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
public class Construction extends Order {

  public Construction(Set<AppUser> employees, Set<Tool> tools, OrderRequestDTO request) {
    super(employees, tools, request);
  }
}
