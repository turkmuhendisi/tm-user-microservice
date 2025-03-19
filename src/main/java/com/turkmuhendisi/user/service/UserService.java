package com.turkmuhendisi.user.service;

import com.turkmuhendisi.user.dto.UserRegistrationDto;
import com.turkmuhendisi.user.exception.EmailAlreadyTakenException;
import com.turkmuhendisi.user.model.User;
import com.turkmuhendisi.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDto from) {
        if (userRepository.findByEmail(from.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException("Email is already taken");
        }

        User user = new User();
        user.setUsername("turkmuhendisi-user");
        user.setEmail(from.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
