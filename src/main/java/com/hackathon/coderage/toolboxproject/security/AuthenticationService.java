package com.hackathon.coderage.toolboxproject.security;


import com.hackathon.coderage.toolboxproject.dto.LoginRequestDTO;

public interface AuthenticationService {

  String authenticate(LoginRequestDTO authenticationRequestDTO);
}
