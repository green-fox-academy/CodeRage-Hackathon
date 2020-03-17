package com.hackathon.coderage.toolboxproject.order;

import com.hackathon.coderage.toolboxproject.dto.OrderRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.OrdersResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.exceptions.NoEmployeeAvailableException;
import com.hackathon.coderage.toolboxproject.exceptions.NoToolAvailableException;
import java.util.List;

public interface OrderService {

  List<Order> listAllOrders();

  List<Order> listAllOrdersByCustomer(String username);

  OrdersResponseDTO listAllOrdersForAdmin();

  OrdersResponseDTO jobsByUser(String username);

  Order createOrder(OrderRequestDTO request, String customerName)
      throws NoEmployeeAvailableException, NoToolAvailableException, IncorrectJobTypeException;
}
