package com.hackathon.coderage.toolboxproject.security;

import com.hackathon.coderage.toolboxproject.appuser.AppUser;
import com.hackathon.coderage.toolboxproject.appuser.AppUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CodeRageUserDetailsService implements UserDetailsService {

  private AppUserRepository appUserRepository;

  @Autowired
  public CodeRageUserDetailsService(
      AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    AppUser appUser = appUserRepository.findAppUserByUsernameEquals(username);
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(appUser.getRole()));
    return new User(
        appUser.getUsername(),
        appUser.getPassword(),
        grantedAuthorities // list of granted authorities
    );
  }
}
