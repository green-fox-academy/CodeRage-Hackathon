package com.hackathon.coderage.toolboxproject.job;

import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

  private JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping(value = "/jobs") // needs admin roles
  public ResponseEntity listAllJobs() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.jobService.listAllJobsForAdmin());
  }

  @GetMapping(value = "/jobs/{username}") // needs privileged roles
  public ResponseEntity listAllJobsByUsername(Principal principal) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.jobService.jobsByUser(principal.getName()));
  }
}
