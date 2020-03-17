package com.hackathon.coderage.toolboxproject.jobtype;

import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;

public interface JobTypeService {

  JobType findJobType(String jobType) throws IncorrectJobTypeException;
}
