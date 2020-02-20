package com.hackathon.coderage.toolboxproject.tool;

import com.hackathon.coderage.toolboxproject.dto.ErrorResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.ResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.ToolRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.ToolResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.ToolsResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolController {

  private ToolService toolService;

  @Autowired
  public ToolController(ToolService toolService) {
    this.toolService = toolService;
  }

  @GetMapping("/tools")
  public ResponseEntity<ToolsResponseDTO> getTools() {
    return ResponseEntity.status(HttpStatus.OK).body(
        new ToolsResponseDTO(
            this.toolService.findAll()
                .stream()
                .map(ToolResponseDTO::new)
                .collect(Collectors.toList())));
  }

  @GetMapping("/tools/{name}")
  public ResponseEntity<ToolsResponseDTO> getToolsByName(
      @PathVariable(name = "name") String name) {
    return ResponseEntity.status(HttpStatus.OK).body(
        new ToolsResponseDTO(
            this.toolService.findAllByName(name)
                .stream()
                .map(ToolResponseDTO::new)
                .collect(Collectors.toList())));
  }

  @PostMapping("/tool")
  public ResponseEntity<ResponseDTO> createTool(
      @RequestBody(required = false) ToolRequestDTO requestDTO) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ToolResponseDTO(this.toolService.addTool(requestDTO)));
    } catch (MissingParameterException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    }
  }

  @DeleteMapping("/tool/{id}")
  public ResponseEntity deleteTool(@PathVariable(name = "id") long toolId) {
    this.toolService.removeToolById(toolId);
    return ResponseEntity.ok().build();
  }
}
