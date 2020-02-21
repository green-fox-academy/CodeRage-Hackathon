package com.hackathon.coderage.toolboxproject.tool;

import com.hackathon.coderage.toolboxproject.dto.ToolRequestDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectIdException;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import java.util.List;

public interface ToolService {

  Tool addTool(ToolRequestDTO requestDTO) throws MissingParameterException;

  void removeToolById(long id) throws IncorrectIdException;

  List<Tool> findAllByName(String name);

  List<Tool> findAll();
}
