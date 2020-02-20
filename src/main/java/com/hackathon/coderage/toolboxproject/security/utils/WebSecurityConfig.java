package com.hackathon.coderage.toolboxproject.security.utils;

import com.hackathon.coderage.toolboxproject.security.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  // region Fields
  private UserDetailsService aragoniteUserDetailsService;
  private JwtRequestFilter jwtRequestFilter;
  // endregion Fields


  // region Constructors
  @Autowired
  public WebSecurityConfig(
      UserDetailsService aragoniteUserDetailsService,
      JwtRequestFilter jwtRequestFilter) {
    this.aragoniteUserDetailsService = aragoniteUserDetailsService;
    this.jwtRequestFilter = jwtRequestFilter;
  }
  // endregion Constructors


  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.aragoniteUserDetailsService);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable()
        .authorizeRequests()
        .antMatchers("/register", "/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
