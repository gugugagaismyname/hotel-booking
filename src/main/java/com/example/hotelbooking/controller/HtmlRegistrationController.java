package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.User;
import com.example.hotelbooking.repository.UserRepository;
import com.example.hotelbooking.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HtmlRegistrationController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RegistrationService registrationService;

  @PostMapping("/register")
  public String handleRegisterForm(
      @RequestParam String username,
      @RequestParam String password,
      Model model) {

    if (!registrationService.registerUser(username, password)) {
      model.addAttribute("error", "Користувач з таким логіном вже існує");
      return "register";
    }

    return "redirect:/login-form";
  }


}

