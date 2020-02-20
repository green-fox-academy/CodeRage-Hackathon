package com.hackathon.coderage.toolboxproject.tool;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ToolRepository extends CrudRepository<Tool, Long> {

  List<Tool> findAllByName(String name);
}
