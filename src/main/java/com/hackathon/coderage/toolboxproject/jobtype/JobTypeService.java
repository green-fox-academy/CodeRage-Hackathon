package com.hackathon.coderage.toolboxproject.jobtype;

import com.hackathon.coderage.toolboxproject.dto.JobTypeRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.JobTypeResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.BadInputException;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectJobTypeException;
import java.util.List;

public interface JobTypeService {

  JobType findJobType(String jobType) throws IncorrectJobTypeException;

  List<JobTypeResponseDTO> listAllJobTypesInDTO();

  JobType createJobType(JobTypeRequestDTO requestDTO) throws BadInputException;

  JobType updateJobType(JobTypeRequestDTO requestDTO, String jobName) throws BadInputException;
}
