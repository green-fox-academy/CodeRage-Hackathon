package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.appuser.AppUserRepository;
import com.hackathon.coderage.toolboxproject.dto.JobsResponseDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

  private JobRepository jobRepository;
  private AppUserRepository appUserRepository;

  public JobServiceImpl(JobRepository jobRepository,
      AppUserRepository appUserRepository) {
    this.jobRepository = jobRepository;
    this.appUserRepository = appUserRepository;
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
    AppUser appUser = this.appUserRepository.findAppUserByUsernameEquals(username);
    return new JobsResponseDTO(appUser.getJobs());
  }
}
