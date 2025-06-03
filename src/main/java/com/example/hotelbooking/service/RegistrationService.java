package com.example.hotelbooking.service;

import com.example.hotelbooking.model.User;
import com.example.hotelbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public boolean registerUser(String username, String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      return false;
    }

    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setRole("ROLE_USER");
    userRepository.save(user);
    return true;
  }
}
