package com.hackathon.coderage.toolboxproject.security;

import jdk.jshell.Snippet.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  @PostMapping("/login")
  public ResponseEntity login(LoginRequestDTO loginRequestDTO){
    try{
      return ResponseEntity.status(HttpStatus.OK).body("asd");
    } catch (Exception e){
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }
}
