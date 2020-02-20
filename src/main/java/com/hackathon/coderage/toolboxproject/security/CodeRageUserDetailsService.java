package com.hackathon.coderage.toolboxproject.security;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.appuser.AppUserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CodeRageUserDetailsService implements UserDetailsService {

  // region Fields
  private AppUserRepository appUserRepository;
  // endregion Fields

  @Autowired
  public CodeRageUserDetailsService(
      AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser appUser = appUserRepository.findByNameIgnoreCase(username);
    return new User(
        appUser.getName(),
        appUser.getPassword(),
        new ArrayList<>()         // list of granted authorities
    );
  }
}
