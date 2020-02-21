package com.hackathon.coderage.toolboxproject.job;

import com.hackathon.coderage.toolboxproject.dto.ErrorResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.JobRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.JobResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.JobsResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.ResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import com.hackathon.coderage.toolboxproject.exceptions.NoEmployeeAvailableException;
import com.hackathon.coderage.toolboxproject.exceptions.NoToolAvailableException;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

  private JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping(value = "/jobs/admin") // needs admin roles
  public ResponseEntity<JobsResponseDTO> listAllJobs() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.jobService.listAllJobsForAdmin());
  }

  @GetMapping(value = "/jobs") // needs privileged roles
  public ResponseEntity<JobsResponseDTO> listAllJobsByUsername(Principal principal) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.jobService.jobsByUser(principal.getName()));
  }

  @PutMapping(value = "/job")
  public ResponseEntity<ResponseDTO> createJob(
      @RequestBody(required = false) JobRequestDTO requestDTO, Principal principal) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new JobResponseDTO(this.jobService.createJob(requestDTO, principal.getName())));
    } catch (NoToolAvailableException | NoEmployeeAvailableException
        | IncorrectJobTypeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    }
  }
}
