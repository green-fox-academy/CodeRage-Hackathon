package com.hackathon.coderage.toolboxproject.jobtype;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepository extends CrudRepository<JobType, Long> {

  JobType findByNameIgnoreCase(String name);

  List<JobType> findAll();
}
