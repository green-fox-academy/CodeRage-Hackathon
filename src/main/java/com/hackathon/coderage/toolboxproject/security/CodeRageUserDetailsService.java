package com.hackathon.coderage.toolboxproject.security;

import com.sun.xml.bind.v2.TODO;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CodeRageUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //TODO get user from repo
    return new User(
        "example user",
        "example password",
        new ArrayList<>()         // list of granted authorities
    );
  }
}
