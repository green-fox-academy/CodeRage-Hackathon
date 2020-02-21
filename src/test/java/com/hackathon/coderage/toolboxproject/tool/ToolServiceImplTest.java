package com.hackathon.coderage.toolboxproject.tool;

import static org.mockito.Mockito.when;

import com.hackathon.coderage.toolboxproject.dto.ToolRequestDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectIdException;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ToolServiceImplTest {

  private ToolServiceImpl toolService;

  @MockBean
  private ToolRepository toolRepository;

  @Before
  public void init() {
    this.toolService = new ToolServiceImpl(toolRepository);
  }

  @Test(expected = MissingParameterException.class)
  public void addTool_MissingName_MissingParameterException() throws MissingParameterException {
    this.toolService.addTool(new ToolRequestDTO("", 0));
  }

  @Test(expected = MissingParameterException.class)
  public void addTool_MissingDTO_MissingParameterException() throws MissingParameterException {
    this.toolService.addTool(null);
  }

  @Test(expected = IncorrectIdException.class)
  public void removeToolById_InvalidId_IncorrectIdException() throws IncorrectIdException {
    when(this.toolRepository.count()).thenReturn(0L);

    this.toolService.removeToolById(1);
  }
}
