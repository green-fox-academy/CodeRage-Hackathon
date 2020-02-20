package com.hackathon.coderage.toolboxproject.tool;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends CrudRepository<Tool, Long> {

  List<Tool> findAllByName(String name);

  List<Tool> findAll();

  @Query(nativeQuery = true,
      value = "SELECT * FROM tools JOIN tools_jobs tj JOIN jobs "
          + "WHERE name LIKE :type "
          + "AND (:endTime < jobs.start_time OR :startTime > jobs.end_time) "
          + "ORDER BY price "
          + "LIMIT :number")
  List<Tool> findAvailable(long startTime, long endTime, String type, int number);
}
