package com.hackathon.coderage.toolboxproject.dto;

import com.hackathon.coderage.toolboxproject.order.Order;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDTO extends ResponseDTO {

  private List<Order> orders;
}
