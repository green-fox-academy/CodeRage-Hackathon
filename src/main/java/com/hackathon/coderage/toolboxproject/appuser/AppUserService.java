package com.hackathon.coderage.toolboxproject.appuser;

import com.hackathon.coderage.toolboxproject.dto.LoginRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.LoginResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.ModificationRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.IncorrectIdException;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import com.hackathon.coderage.toolboxproject.exceptions.UsernameAlreadyTakenException;
import java.util.List;
import org.springframework.security.authentication.BadCredentialsException;

public interface AppUserService {

  AppUser save(AppUser appUser);

  RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO)
      throws UsernameAlreadyTakenException, MissingParameterException;

  LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws BadCredentialsException;

  AppUser changeUserRole(ModificationRequestDTO requestDTO) throws MissingParameterException;

  void deleteUserById(long id) throws IncorrectIdException;

  List<AppUser> findAllEmployees();

  AppUser findByUsername(String username);
}
