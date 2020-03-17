package com.hackathon.coderage.toolboxproject.order;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.appuser.AppUserService;
import com.hackathon.coderage.toolboxproject.dto.OrderRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.OrdersResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.exceptions.NoEmployeeAvailableException;
import com.hackathon.coderage.toolboxproject.exceptions.NoToolAvailableException;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import com.hackathon.coderage.toolboxproject.tool.ToolService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;
  private AppUserService appUserService;
  private ToolService toolService;

  public OrderServiceImpl(OrderRepository orderRepository,
      AppUserService appUserService, ToolService toolService) {
    this.orderRepository = orderRepository;
    this.appUserService = appUserService;
    this.toolService = toolService;
  }

  @Override
  public List<Order> listAllOrders() {
    return this.orderRepository.findAll();
  }

  @Override
  public List<Order> listAllOrdersByCustomer(String username) {
    return this.orderRepository.findAllByCustomerUsername(username);
  }

  @Override
  public OrdersResponseDTO listAllOrdersForAdmin() {
    return new OrdersResponseDTO(this.listAllOrders());
  }

  @Override
  public OrdersResponseDTO jobsByUser(String username) {
    AppUser appUser = this.appUserService.findByUsername(username);
    return new OrdersResponseDTO(appUser.getOrders());
  }

  private Set<AppUser> findAvailableEmployeesOnDate(long date) throws NoEmployeeAvailableException {
    List<AppUser> employees = new ArrayList<>(this.appUserService.findAllEmployees());
    this.orderRepository.findAllByDate(date)
        .forEach(order -> order.getEmployees()
            .forEach(employees::remove));
    if (employees.size() == 0) {
      throw new NoEmployeeAvailableException();
    }
    return new HashSet<>(employees);
  }

  private Set<Tool> findAvailableToolsOnDate(long date) throws NoToolAvailableException {
    List<Tool> tools = new ArrayList<>(this.toolService.findAll());
    this.orderRepository.findAllByDate(date)
        .forEach(order -> order.getTools()
            .forEach(tools::remove));
    if (tools.size() == 0) {
      throw new NoToolAvailableException();
    }
    return new HashSet<>(tools);
  }

  @Override
  public Order createOrder(OrderRequestDTO request, String customerName)
      throws NoEmployeeAvailableException, NoToolAvailableException, IncorrectJobTypeException {
    // TODO date validation
    AppUser customer = this.appUserService.findByUsername(customerName);
    Set<Tool> tools = this.findAvailableToolsOnDate(request.getDate());
    Set<AppUser> employees = this.findAvailableEmployeesOnDate(request.getDate());
    Order order = OrderFactory.orderJob(request.getType(), employees, tools, request);
    order.setCustomer(customer);
    return this.orderRepository.save(order);
  }
}
