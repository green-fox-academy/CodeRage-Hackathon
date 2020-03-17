package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.order.Order;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDTO extends ResponseDTO {

  private long id;
  private String customerName;
  private String type;
  private String status;
  private long price;
  private Set<ToolResponseDTO> tools;
  private Set<UserResponseDTO> employees;

  public OrderResponseDTO(Order order) {
    this.id = order.getId();
    this.customerName = order.getCustomer().getFullName();
    this.type = order.getClass().getSimpleName();
    this.status = order.getStatus();
    this.price = order.getPrice();
    this.tools = order.getTools()
        .stream()
        .map(ToolResponseDTO::new)
        .collect(Collectors.toSet());
    this.employees = order.getEmployees()
        .stream()
        .map(UserResponseDTO::new)
        .collect(Collectors.toSet());
  }
}
