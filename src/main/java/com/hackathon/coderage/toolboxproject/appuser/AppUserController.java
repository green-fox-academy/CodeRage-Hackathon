package com.hackathon.coderage.toolboxproject.appuser;

import com.hackathon.coderage.toolboxproject.dto.ErrorResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.LoginRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.ModificationRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.ResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.UserResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectIdException;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import com.hackathon.coderage.toolboxproject.exceptions.UsernameAlreadyTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PostMapping("/login")
  public ResponseEntity loginUser(@RequestBody(required = false) LoginRequestDTO loginRequestDTO) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(appUserService.login(loginRequestDTO));
    } catch (BadCredentialsException | UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", "Username or password incorrect!"));
    }
  }

  @PutMapping(value = "/user")
  public ResponseEntity<ResponseDTO> modifyUserRole(
      @RequestBody(required = false) ModificationRequestDTO requestDTO) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new UserResponseDTO(this.appUserService.changeUserRole(requestDTO)));
    } catch (MissingParameterException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    }
  }

  @DeleteMapping(value = "/user/{id}")
  public ResponseEntity deleteUserById(@PathVariable(name = "id") long userId) {
    try {
      this.appUserService.deleteUserById(userId);
    } catch (IncorrectIdException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponseDTO("error", e.getMessage()));
    }
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
