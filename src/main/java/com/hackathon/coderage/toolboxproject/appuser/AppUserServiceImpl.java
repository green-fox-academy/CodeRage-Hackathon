package com.hackathon.coderage.toolboxproject.appuser;

import com.hackathon.coderage.toolboxproject.dto.RegisterRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import com.hackathon.coderage.toolboxproject.exceptions.UsernameAlreadyTakenException;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

  private AppUserRepository appUserRepository;

  public AppUserServiceImpl(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public AppUser save(AppUser appUser) {
    return this.appUserRepository.save(appUser);
  }

  private AppUser registerAppUser(RegisterRequestDTO registerRequestDTO) {
    return this.appUserRepository.save(
        new AppCustomer(
            registerRequestDTO.getUsername(),
            registerRequestDTO.getPassword(),
            registerRequestDTO.getFullName())
    );
  }

  @Override
  public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO)
      throws UsernameAlreadyTakenException, MissingParameterException {
    validateRegistration(registerRequestDTO);
    return new RegisterResponseDTO(this.registerAppUser(registerRequestDTO));
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
