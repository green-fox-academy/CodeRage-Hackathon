package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderAttributeResponseDTO extends ResponseDTO {

  private String customer;
  private String type;
  private String status;

  public OrderAttributeResponseDTO(Order order) {
    this.customer = order.getCustomer().getFullName();
    this.type = order.getClass().getSimpleName();
    this.status = order.getStatus();
  }
}
