package com.turkmuhendisi.user.service;

import com.turkmuhendisi.user.dto.UserRegistrationDto;
import com.turkmuhendisi.user.dto.UserResponse;
import com.turkmuhendisi.user.exception.EmailAlreadyTakenException;
import com.turkmuhendisi.user.exception.EmailNotFoundException;
import com.turkmuhendisi.user.model.User;
import com.turkmuhendisi.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRegistrationDto from) {
        if (userRepository.findByEmail(from.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException("Email is already taken");
        }

        User user = new User(
                "turkmuhendisi-user",
                from.getEmail(),
                passwordEncoder.encode(from.getPassword())
        );

        userRepository.save(user);
    }

    public UserResponse getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("Email not found!"));
        return new UserResponse(
                Objects.requireNonNull(user.getEmail()),
                Objects.requireNonNull(user.getPassword()),
                Objects.requireNonNull(user.getAuthorities())
        );
    }

}
