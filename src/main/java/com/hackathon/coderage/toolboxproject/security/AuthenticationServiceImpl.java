package com.hackathon.coderage.toolboxproject.security;

import com.hackathon.coderage.toolboxproject.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  // region Fields
  private AuthenticationManager authenticationManager;
  private JwtUtil jwtTokenUtil;
  private CodeRageUserDetailsService userDetailsService;
  // endregion Fields

  @Autowired
  public AuthenticationServiceImpl(
      AuthenticationManager authenticationManager,
      JwtUtil jwtTokenUtil,
      CodeRageUserDetailsService userDetailsService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDetailsService = userDetailsService;
  }

  public String authenticate(LoginRequestDTO loginRequestDTO) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequestDTO.getUsername(),
              loginRequestDTO.getPassword()
          )
      );
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Incorrect username or password");
    }

    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(loginRequestDTO.getUsername());

    return jwtTokenUtil.generateToken(userDetails);
  }
}
