package com.example.hotelbooking.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;


import com.example.hotelbooking.repository.UserRepository;
import com.example.hotelbooking.model.User;

import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath().startsWith("/api/auth/")) {
      filterChain.doFilter(request, response);
      return;
    }

    log.info("ServletPath: {}", request.getServletPath());

    final String authHeader = request.getHeader("Authorization");
    String username = null;
    String jwt = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      jwt = authHeader.substring(7);
      try {
        username = jwtUtil.extractUsername(jwt);
      } catch (Exception e) {
        System.out.println("Unable to extract JWT: " + e.getMessage());
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      final String token = jwt;
      userRepository.findByUsername(username).ifPresent(dbUser -> {
        if (jwtUtil.isTokenValid(token)) {
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(
                  dbUser.getUsername(),
                  null,
                  Collections.singletonList(new SimpleGrantedAuthority(dbUser.getRole()))
              );
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      });
    }

    filterChain.doFilter(request, response);
  }
}
