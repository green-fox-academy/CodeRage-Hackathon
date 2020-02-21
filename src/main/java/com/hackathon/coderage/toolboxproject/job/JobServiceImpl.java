package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.appuser.AppUserService;
import com.hackathon.coderage.toolboxproject.dto.JobsResponseDTO;
import com.hackathon.coderage.toolboxproject.tool.ToolService;
import java.util.List;
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
}
