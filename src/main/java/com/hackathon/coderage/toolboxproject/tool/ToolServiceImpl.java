package com.hackathon.coderage.toolboxproject.tool;

import com.hackathon.coderage.toolboxproject.dto.ToolRequestDTO;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolServiceImpl implements ToolService {

  private ToolRepository toolRepository;

  @Autowired
  public ToolServiceImpl(ToolRepository toolRepository) {
    this.toolRepository = toolRepository;
  }

  @Override
  public Tool addTool(ToolRequestDTO requestDTO) throws MissingParameterException {
    if (requestDTO == null || requestDTO.getName().isBlank()) {
      throw new MissingParameterException("Missing parameters!");
    }
    return this.toolRepository.save(new Tool(requestDTO.getName(), requestDTO.getHourlyPrice()));
  }

  @Override
  public void removeToolById(long id) {
    this.toolRepository.deleteById(id);
  }

  @Override
  public List<Tool> findAllByName(String name) {
    return this.toolRepository.findAllByName(name);
  }

  @Override
  public List<Tool> findAll() {
    return this.toolRepository.findAll();
  }
}
