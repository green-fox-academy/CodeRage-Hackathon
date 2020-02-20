package com.hackathon.coderage.toolboxproject.appuser;

import com.hackathon.coderage.toolboxproject.dto.ModificationRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterResponseDTO;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import com.hackathon.coderage.toolboxproject.exceptions.UsernameAlreadyTakenException;

public interface AppUserService {

  AppUser save(AppUser appUser);

  RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO)
      throws UsernameAlreadyTakenException, MissingParameterException;

  AppUser changeUserRole(ModificationRequestDTO requestDTO) throws MissingParameterException;

  void deleteUserById(long id);
}
