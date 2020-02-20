package com.hackathon.coderage.toolboxproject.job;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

  List<Job> findAllByWorker_Id(long id);
}
