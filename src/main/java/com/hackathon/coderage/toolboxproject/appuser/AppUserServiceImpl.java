package com.hackathon.coderage.toolboxproject.appuser;

import com.hackathon.coderage.toolboxproject.dto.LoginRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.LoginResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import com.hackathon.coderage.toolboxproject.exceptions.UsernameAlreadyTakenException;
import com.hackathon.coderage.toolboxproject.security.AuthenticationService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

  private AppUserRepository appUserRepository;
  private AuthenticationService authenticationService;
  private PasswordEncoder passwordEncoder;

  public AppUserServiceImpl(
      AppUserRepository appUserRepository,
      AuthenticationService authenticationService,
      PasswordEncoder passwordEncoder) {
    this.appUserRepository = appUserRepository;
    this.authenticationService = authenticationService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public AppUser save(AppUser appUser) {
    return this.appUserRepository.save(appUser);
  }

  private AppUser registerAppUser(RegisterRequestDTO registerRequestDTO) {
    AppUser appUser = new AppUser(
        registerRequestDTO.getUsername(),
        passwordEncoder.encode(registerRequestDTO.getPassword()),
        registerRequestDTO.getFullName(),
        registerRequestDTO.getQualification());
    appUser.setRole("baseUser");
    return this.appUserRepository.save(appUser);
  }

  @Override
  public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO)
      throws UsernameAlreadyTakenException, MissingParameterException {
    validateRegistration(registerRequestDTO);
    return new RegisterResponseDTO(this.registerAppUser(registerRequestDTO));
  }

  @Override
  public LoginResponseDTO login(LoginRequestDTO loginRequestDTO)
      throws BadCredentialsException, UsernameNotFoundException {
    return new LoginResponseDTO(authenticationService.authenticate(loginRequestDTO));
  }

  public void validateRegistration(RegisterRequestDTO registerRequestDTO)
      throws MissingParameterException, UsernameAlreadyTakenException {
    if (registerRequestDTO.getUsername().isBlank() || registerRequestDTO.getPassword().isBlank()) {
      throw new MissingParameterException("Username or password is missing or invalid!");
    } else if (this.appUserRepository.existsByUsernameEquals(registerRequestDTO.getUsername())) {
      throw new UsernameAlreadyTakenException("Username already taken!");
    } else if (registerRequestDTO.getFullName().isBlank()) {
      throw new MissingParameterException("Full name is missing or invalid!");
    } else if (registerRequestDTO.getQualification().isBlank()) {
      throw new MissingParameterException("Qualification is missing or invalid!");
    }
  }
}
