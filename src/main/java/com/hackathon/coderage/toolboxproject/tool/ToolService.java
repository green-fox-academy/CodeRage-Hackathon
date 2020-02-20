package com.hackathon.coderage.toolboxproject.tool;

import com.hackathon.coderage.toolboxproject.dto.ToolRequestDTO;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import java.util.List;

public interface ToolService {

  List<Tool> findAvailable(long startTime, long endTime, String type, int number);

  Tool addTool(ToolRequestDTO requestDTO) throws MissingParameterException;

  void removeToolById(long id);

  List<Tool> findAllByName(String name);

  List<Tool> findAll();
}
