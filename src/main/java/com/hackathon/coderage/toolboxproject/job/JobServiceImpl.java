package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.appuser.AppUserService;
import com.hackathon.coderage.toolboxproject.dto.JobRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.JobsResponseDTO;
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
public class JobServiceImpl implements JobService {

  private JobRepository jobRepository;
  private AppUserService appUserService;
  private ToolService toolService;

  public JobServiceImpl(JobRepository jobRepository,
      AppUserService appUserService, ToolService toolService) {
    this.jobRepository = jobRepository;
    this.appUserService = appUserService;
    this.toolService = toolService;
  }

  @Override
  public List<Job> listAllJobs() {
    return this.jobRepository.findAll();
  }

  @Override
  public List<Job> listAllJobsByCustomer(String username) {
    return this.jobRepository.findAllByCustomerUsername(username);
  }

  @Override
  public JobsResponseDTO listAllJobsForAdmin() {
    return new JobsResponseDTO(this.listAllJobs());
  }

  @Override
  public JobsResponseDTO jobsByUser(String username) {
    AppUser appUser = this.appUserService.findByUsername(username);
    return new JobsResponseDTO(appUser.getJobs());
  }

  private Set<AppUser> findAvailableEmployeesOnDate(long date) throws NoEmployeeAvailableException {
    List<AppUser> employees = new ArrayList<>(this.appUserService.findAllEmployees());
    this.jobRepository.findAllByDate(date)
        .forEach(job -> job.getEmployees()
            .forEach(employees::remove));
    if (employees.size() == 0) {
      throw new NoEmployeeAvailableException();
    }
    return new HashSet<>(employees);
  }

  private Tool findAvailableToolsOnDate(long date) throws NoToolAvailableException {
    List<Tool> tools = new ArrayList<>(this.toolService.findAll());
    this.jobRepository.findAllByDate(date).forEach(job -> tools.remove(job.getTool()));
    if (tools.size() == 0) {
      throw new NoToolAvailableException();
    }
    return tools.get(0);
  }

  @Override
  public Job createJob(JobRequestDTO request, String customerName)
      throws NoEmployeeAvailableException, NoToolAvailableException, IncorrectJobTypeException {
    // TODO date validation
    AppUser customer = this.appUserService.findByUsername(customerName);
    Tool tool = this.findAvailableToolsOnDate(request.getDate());
    Set<AppUser> employee = this.findAvailableEmployeesOnDate(request.getDate());
    Job job = JobFactory.orderJob(request.getType(), employee, tool, request);
    job.setCustomer(customer);
    return this.jobRepository.save(job);
  }
}
