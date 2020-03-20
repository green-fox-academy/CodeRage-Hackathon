package com.hackathon.coderage.toolboxproject.jobtype;

import com.hackathon.coderage.toolboxproject.dto.JobTypeRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.JobTypeResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.BadInputException;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class JobTypeServiceImp implements JobTypeService {

  private JobTypeRepository repository;

  public JobTypeServiceImp(
      JobTypeRepository repository) {
    this.repository = repository;
  }

  @Override
  public JobType findJobType(String jobType) throws IncorrectJobTypeException {
    JobType type = this.repository.findByNameIgnoreCase(jobType);
    if (type == null) {
      throw new IncorrectJobTypeException();
    }
    return type;
  }

  @Override
  public List<JobTypeResponseDTO> listAllJobTypesInDTO() {
    return this.repository.findAll()
        .stream()
        .map(JobTypeResponseDTO::new)
        .collect(Collectors.toList());
  }

  @Override
  public JobType createJobType(JobTypeRequestDTO requestDTO) throws BadInputException {
    if (requestDTO == null ||
        requestDTO.getName().isBlank() ||
        requestDTO.getRequiredTools().isBlank() ||
        requestDTO.getRequiredQualification().isBlank()) {
      throw new BadInputException();
    }

    return this.repository.save(
        new JobType(requestDTO.getName(),
            requestDTO.getCrewSize(),
            requestDTO.getRequiredTools(),
            requestDTO.getRequiredQualification()));
  }

  @Override
  public JobType updateJobType(JobTypeRequestDTO requestDTO, String jobName)
      throws BadInputException {
    if (requestDTO == null || jobName.isBlank()) {
      throw new BadInputException();
    }
    boolean wasModified = false;

    JobType jobType = this.repository.findByNameIgnoreCase(jobName);

    if (jobType == null) {
      throw new BadInputException();
    }
    if (requestDTO.getName() != null && !requestDTO.getName().isBlank()) {
      jobType.setName(requestDTO.getName());
      wasModified = true;
    }
    if (requestDTO.getRequiredQualification() != null &&
        !requestDTO.getRequiredQualification().isBlank()) {
      jobType.setRequiredQualification(requestDTO.getRequiredQualification());
      wasModified = true;
    }
    if (requestDTO.getRequiredTools() != null &&
        !requestDTO.getRequiredTools().isBlank()) {
      jobType.setRequiredTools(requestDTO.getRequiredTools());
      wasModified = true;
    }
    if (requestDTO.getCrewSize() != 0) {
      jobType.setCrewSize(requestDTO.getCrewSize());
      wasModified = true;
    }
    if (wasModified) {
      return this.repository.save(jobType);
    } else {
      throw new BadInputException();
    }
  }

}
