package com.hackathon.coderage.toolboxproject.appuser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.coderage.toolboxproject.dto.LoginRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.LoginResponseDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterRequestDTO;
import com.hackathon.coderage.toolboxproject.dto.RegisterResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RegisterLoginIntegrationTest {

  private ObjectMapper objectMapper = new ObjectMapper();
  private TestRestTemplate testRestTemplate = new TestRestTemplate();

  @Autowired
  private AppUserRepository appUserRepository;


  @Test
  public void registerAndLoginUser_CreateUserGetToken() throws JsonProcessingException {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO(
        "name", "pw", "fuuuuullNaaame", "qualific");

    String registerURL = "http://localhost:8081/register";
    ResponseEntity<RegisterResponseDTO> registerResponse = this.testRestTemplate
        .postForEntity(
            registerURL,
            registerRequestDTO,
            RegisterResponseDTO.class);

    RegisterResponseDTO registerResponseDTO = registerResponse.getBody();
    assertNotNull(registerResponseDTO);

    AppUser appUser = this.appUserRepository.findByUsername(registerResponseDTO.getUsername());

    assertEquals(
        this.objectMapper.writeValueAsString(new RegisterResponseDTO(appUser)),
        this.objectMapper.writeValueAsString(registerResponseDTO));

    LoginRequestDTO loginRequestDTO = new LoginRequestDTO(
        "name", "pw");

    String loginURL = "http://localhost:8081/login";
    ResponseEntity<LoginResponseDTO> loginResponse = this.testRestTemplate
        .postForEntity(
            loginURL,
            loginRequestDTO,
            LoginResponseDTO.class);
    assertNotNull(loginResponse.getBody());

    String token = loginResponse.getBody().getJwt();
    assertNotNull(token);
  }
}
