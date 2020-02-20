package com.hackathon.coderage.toolboxproject.appuser;

import com.hackathon.coderage.toolboxproject.dto.ErrorResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterRequestDTO;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import com.hackathon.coderage.toolboxproject.exceptions.UsernameAlreadyTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {

  private AppUserService appUserService;

  public AppUserController(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  @PostMapping(value = "/register")
  public ResponseEntity registerUser(
      @RequestBody(required = false) RegisterRequestDTO registerRequestDTO) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(this.appUserService.register(registerRequestDTO));
    } catch (MissingParameterException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    } catch (UsernameAlreadyTakenException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    }
  }
}
