package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.User;
import com.example.hotelbooking.repository.UserRepository;
import com.example.hotelbooking.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RegistrationService registrationService;

  @PostMapping("/register")
  public String registerUser(@RequestBody User user) {
    if (!registrationService.registerUser(user.getUsername(), user.getPassword())) {
      return "Username already exists";
    }
    return "User registered successfully";
  }

}
