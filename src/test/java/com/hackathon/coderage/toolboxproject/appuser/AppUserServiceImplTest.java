package com.hackathon.coderage.toolboxproject.appuser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.hackathon.coderage.toolboxproject.dto.LoginRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.LoginResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.ModificationRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterRequestDTO;
import com.hackathon.coderage.toolboxproject.exceptions.MissingParameterException;
import com.hackathon.coderage.toolboxproject.exceptions.UsernameAlreadyTakenException;
import com.hackathon.coderage.toolboxproject.security.AuthenticationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AppUserServiceImplTest {

  private AppUserServiceImpl appUserService;

  @MockBean
  private AppUserRepository appUserRepository;
  @MockBean
  private AuthenticationService authenticationService;
  @MockBean
  private PasswordEncoder passwordEncoder;

  @Before
  public void init() {
    appUserService = new AppUserServiceImpl(
        appUserRepository,
        authenticationService,
        passwordEncoder
    );
  }

  @Test
  public void login_CorrectCredentials_ReturnJWT() {
    when(authenticationService.authenticate(any())).thenReturn("i'm a JWT");
    Assert.assertEquals(new LoginResponseDTO("i'm a JWT"),
        appUserService.login(new LoginRequestDTO("a", "b")));
  }

  @Test(expected = BadCredentialsException.class)
  public void login_IncorrectCredentials_ThrowsBadCredentialsException() throws Exception {
    when(authenticationService.authenticate(any()))
        .thenThrow(new BadCredentialsException("smasth"));
    appUserService.login(new LoginRequestDTO("asd", "asd"));
  }

  @Test
  public void changeUserRole_CorrectCredentials_UserRoleChanged() throws Exception {
    AppUser appUser = new AppUser(
        "asd", "asd", "asd", "asd");
    when(appUserRepository.findById(1)).thenReturn(appUser);
    when(appUserRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    appUser.setRole("Admin");

    Assert.assertEquals(appUser,
        appUserService.changeUserRole(new ModificationRequestDTO("Admin", 1)));
  }

  @Test(expected = MissingParameterException.class)
  public void changeUserRole_RequestDTOIsNull_MissingParameterException()
      throws Exception {
    appUserService.changeUserRole(null);
  }

  @Test(expected = MissingParameterException.class)
  public void register_MissingUsername_ThrowsMissingParameterException()
      throws UsernameAlreadyTakenException, MissingParameterException {
    appUserService.register(new RegisterRequestDTO(
        "", "asd", "asd", "asd"));
  }

  @Test(expected = MissingParameterException.class)
  public void register_MissingPassword_ThrowsMissingParameterException()
      throws UsernameAlreadyTakenException, MissingParameterException {
    appUserService.register(new RegisterRequestDTO(
        "asd", "", "asd", "asd"));
  }

  @Test(expected = MissingParameterException.class)
  public void register_MissingFullName_ThrowsMissingParameterException()
      throws UsernameAlreadyTakenException, MissingParameterException {
    appUserService.register(new RegisterRequestDTO(
        "asd", "asd", "", "asd"));
  }

  @Test(expected = MissingParameterException.class)
  public void register_MissingQualifications_ThrowsMissingParameterException()
      throws UsernameAlreadyTakenException, MissingParameterException {
    appUserService.register(new RegisterRequestDTO(
        "asd", "asd", "asd", ""));
  }
}
