package com.hackathon.coderage.toolboxproject.security;


public interface AuthenticationService {

  String authenticate(LoginRequestDTO authenticationRequestDTO);
}
