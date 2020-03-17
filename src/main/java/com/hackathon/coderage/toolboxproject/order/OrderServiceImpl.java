package com.hackathon.coderage.toolboxproject.order;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.appuser.AppUserService;
import com.hackathon.coderage.toolboxproject.dto.OrderRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.OrdersResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.exceptions.NoEmployeeAvailableException;
import com.hackathon.coderage.toolboxproject.exceptions.NoToolAvailableException;
import com.hackathon.coderage.toolboxproject.jobtype.JobType;
import com.hackathon.coderage.toolboxproject.jobtype.JobTypeService;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import com.hackathon.coderage.toolboxproject.tool.ToolService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;
  private AppUserService appUserService;
  private ToolService toolService;
  private JobTypeService jobTypeService;

  public OrderServiceImpl(OrderRepository orderRepository,
      AppUserService appUserService,
      ToolService toolService,
      JobTypeService jobTypeService) {
    this.orderRepository = orderRepository;
    this.appUserService = appUserService;
    this.toolService = toolService;
    this.jobTypeService = jobTypeService;
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

  private List<AppUser> findAvailableEmployeesOnDate(long date) {
    List<AppUser> employees = new ArrayList<>(this.appUserService.findAllEmployees());
    this.orderRepository.findAllByDate(date)
        .forEach(order -> order.getEmployees()
            .forEach(employees::remove));
    return employees;
  }

  @Override
  public Order createOrder(OrderRequestDTO request, String customerName)
      throws NoEmployeeAvailableException, NoToolAvailableException, IncorrectJobTypeException {
    JobType jobType = this.jobTypeService.findJobType(request.getType());
    Set<AppUser> employees = this.findCrewForOrder(request.getDate(), jobType);
    Set<Tool> tools = findToolsForOrder(request.getDate(), jobType);
    AppUser customer = this.appUserService.findByUsername(customerName);

    return this.orderRepository.save(new Order(customer, employees, tools, request.getDate()));
  }

  private Set<AppUser> findCrewForOrder(long date, JobType jobType)
      throws NoEmployeeAvailableException {
    Set<AppUser> employees = this.findAvailableEmployeesOnDate(date).stream()
        .filter(employee -> employee.getQualification()
            .contains(jobType.getRequiredQualification()))
        .limit(jobType.getCrewSize())
        .collect(Collectors.toSet());
    if (employees.size() != jobType.getCrewSize()) {
      throw new NoEmployeeAvailableException();
    }
    return employees;
  }

  private Set<Tool> findToolsForOrder(long date, JobType jobType) throws NoToolAvailableException {
    Set<Tool> availableTools = new HashSet<>(this.toolService.findAll());
    this.orderRepository.findAllByDate(date)
        .forEach(order -> order.getTools()
            .forEach(availableTools::remove));

    Set<Tool> foundTools = new HashSet<>();
    String[] requiredTools = jobType.getRequiredTools().split(";");
    for (String tool : requiredTools) {
      Optional<Tool> tempTool = availableTools.stream()
          .filter(item -> item.getName().equalsIgnoreCase(tool))
          .findFirst();
      if (tempTool.isEmpty()) {
        throw new NoToolAvailableException();
      }
      foundTools.add(tempTool.get());
      availableTools.remove(tempTool.get());
    }
    return foundTools;
  }
}
