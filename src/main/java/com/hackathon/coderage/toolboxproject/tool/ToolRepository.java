package com.hackathon.coderage.toolboxproject.tool;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends CrudRepository<Tool, Long> {

  List<Tool> findAllByNameOrderByDailyPriceAsc(String name);

  List<Tool> findAll();
}
