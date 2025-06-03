package com.example.hotelbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

  @GetMapping("/register")
  public String showRegisterForm() {
    return "register";
  }

  @GetMapping("/login-form")
  public String showLoginForm() {
    return "login";
  }
}
