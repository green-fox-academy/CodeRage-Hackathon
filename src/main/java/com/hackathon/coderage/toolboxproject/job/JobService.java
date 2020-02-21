package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.dto.JobRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.JobsResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.exceptions.NoEmployeeAvailableException;
import com.hackathon.coderage.toolboxproject.exceptions.NoToolAvailableException;
import com.hackathon.coderage.toolboxproject.tool.Tool;
import java.util.List;

public interface JobService {

  List<Job> listAllJobs();

  List<Job> listAllJobsByCustomer(String username);

  JobsResponseDTO listAllJobsForAdmin();

  JobsResponseDTO jobsByUser(String username);

  AppUser findAvailableEmployeesOnDate(long date) throws NoEmployeeAvailableException;

  Tool findAvailableToolsOnDate(long date) throws NoToolAvailableException;

  Job createJob(JobRequestDTO request, String customerName)
      throws NoEmployeeAvailableException, NoToolAvailableException, IncorrectJobTypeException;
}
