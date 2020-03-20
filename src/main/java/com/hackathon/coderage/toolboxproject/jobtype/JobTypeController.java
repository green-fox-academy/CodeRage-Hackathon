package com.hackathon.coderage.toolboxproject.jobtype;

import com.hackathon.coderage.toolboxproject.dto.ErrorResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.JobTypeRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.JobTypeResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.ResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.BadInputException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobTypeController {

  private JobTypeService jobTypeService;

  @Autowired
  public JobTypeController(JobTypeService jobTypeService) {
    this.jobTypeService = jobTypeService;
  }

  @GetMapping("/job_types")
  public ResponseEntity<List<JobTypeResponseDTO>> listAllJobTypes() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.jobTypeService.listAllJobTypesInDTO());
  }

  @PostMapping("/job_type")
  public ResponseEntity<ResponseDTO> createJobType(
      @RequestBody JobTypeRequestDTO requestDTO) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new JobTypeResponseDTO(this.jobTypeService.createJobType(requestDTO)));
    } catch (BadInputException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    }
  }

  @PutMapping("/job_type/{name}")
  public ResponseEntity<ResponseDTO> updateJobType(@PathVariable(name = "name") String name,
      @RequestBody(required = false) JobTypeRequestDTO requestDTO) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new JobTypeResponseDTO(this.jobTypeService.updateJobType(requestDTO, name)));
    } catch (BadInputException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    }
  }
}
