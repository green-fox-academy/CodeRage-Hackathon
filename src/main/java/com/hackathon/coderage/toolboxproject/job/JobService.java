package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.dto.JobsResponseDTO;
import java.util.List;

public interface JobService {

  List<Job> listAllJobs();

  List<Job> listAllJobsByCustomer(String username);

  JobsResponseDTO listAllJobsForAdmin();

  JobsResponseDTO jobsByUser(String username);
}
