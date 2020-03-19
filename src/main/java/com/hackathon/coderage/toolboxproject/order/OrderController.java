package com.hackathon.coderage.toolboxproject.order;

import com.hackathon.coderage.toolboxproject.dto.ErrorResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.OrderRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.OrderResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.OrdersResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.ResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.exceptions.NoEmployeeAvailableException;
import com.hackathon.coderage.toolboxproject.exceptions.NoToolAvailableException;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping(value = "/orders/admin") // needs admin roles
  public ResponseEntity<OrdersResponseDTO> listAllOrders() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.orderService.listAllOrdersForAdmin());
  }

  @GetMapping(value = "/orders") // needs privileged roles
  public ResponseEntity<OrdersResponseDTO> listAllOrdersByUsername(Principal principal) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.orderService.jobsByUser(principal.getName()));
  }

  @PostMapping(value = "/order")
  public ResponseEntity<ResponseDTO> createOrder(
      @RequestBody(required = false) OrderRequestDTO requestDTO, Principal principal) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new OrderResponseDTO(
              this.orderService.createOrder(requestDTO, principal.getName())));
    } catch (NoToolAvailableException | NoEmployeeAvailableException |
        IncorrectJobTypeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    }
  }
}
