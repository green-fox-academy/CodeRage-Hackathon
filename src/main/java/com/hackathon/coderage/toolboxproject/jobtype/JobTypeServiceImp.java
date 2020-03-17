package com.hackathon.coderage.toolboxproject.jobtype;

import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
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
}
