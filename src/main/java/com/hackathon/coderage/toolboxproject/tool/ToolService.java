package com.hackathon.coderage.toolboxproject.tool;

import java.util.List;

public interface ToolService {

  List<Tool> findAvailable(long startTime, long endTime, String type, int number);

  Tool addTool(String name, int hourlyPrice);

  void removeToolById(long id);

  List<Tool> findAllByName(String name);

  List<Tool> findAll();
}
