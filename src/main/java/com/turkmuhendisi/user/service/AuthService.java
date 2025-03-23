package com.turkmuhendisi.user.service;

import com.turkmuhendisi.user.dto.UserResponse;
import com.turkmuhendisi.user.model.User;
import com.turkmuhendisi.user.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse login(String email, String rawPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword())) {
            return new UserResponse(
                    user.get().getEmail(),
                    user.get().getPassword(),
                    user.get().getAuthorities()
            );
        }
        throw new BadCredentialsException("Invalid credentials!");
    }
}
