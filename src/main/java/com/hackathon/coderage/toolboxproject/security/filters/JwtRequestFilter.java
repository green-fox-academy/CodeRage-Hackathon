package com.hackathon.coderage.toolboxproject.security.filters;

import static com.hackathon.coderage.toolboxproject.security.utils.JwtUtil.AUTHORIZATION_HEADER;
import static com.hackathon.coderage.toolboxproject.security.utils.JwtUtil.TOKEN_BEARER;

import com.hackathon.coderage.toolboxproject.security.CodeRageUserDetailsService;
import com.hackathon.coderage.toolboxproject.security.utils.JwtUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private CodeRageUserDetailsService codeRageUserDetailsService;
  private JwtUtil jwtUtil;

  @Autowired
  public JwtRequestFilter(
      CodeRageUserDetailsService codeRageUserDetailsService,
      JwtUtil jwtUtil) {
    this.codeRageUserDetailsService = codeRageUserDetailsService;
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain)
      throws ServletException, IOException {

    final String header = request.getHeader(AUTHORIZATION_HEADER);
    String username = null;
    String jwt = null;

    if (header != null && header.startsWith(TOKEN_BEARER)) {
      jwt = header.substring(7);
      username = this.jwtUtil.extractUsername(jwt);
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (username != null && authentication == null) {
      UserDetails userDetails = this.codeRageUserDetailsService.loadUserByUsername(username);

      if (this.jwtUtil.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken unPwAuthToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities());
        unPwAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder
            .getContext()
            .setAuthentication(unPwAuthToken);
      }
    }
    chain.doFilter(request, response);
  }
}
