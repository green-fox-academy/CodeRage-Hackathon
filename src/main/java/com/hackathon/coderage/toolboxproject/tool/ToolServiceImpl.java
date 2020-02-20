package com.hackathon.coderage.toolboxproject.tool;

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
  public List<Tool> findAvailable(long startTime, long endTime, String type, int number) {
    return this.toolRepository.findAvailable(startTime, endTime, type, number);
  }

  @Override
  public Tool addTool(String name, int hourlyPrice) {
    return this.toolRepository.save(new Tool(name, hourlyPrice));
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
