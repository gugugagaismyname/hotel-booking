package com.example.hotelbooking.controller;

import com.example.hotelbooking.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.example.hotelbooking.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  @PostMapping("/login")
  public String login(@RequestBody LoginRequest request) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );
    } catch (AuthenticationException e) {
      throw new RuntimeException("Invalid credentials");
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
    final String token = jwtUtil.generateToken(userDetails.getUsername());
    return "Bearer " + token;
  }
}
