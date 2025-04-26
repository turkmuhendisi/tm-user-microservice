package com.turkmuhendisi.user.service;

import com.turkmuhendisi.user.controller.AuthClient;
import com.turkmuhendisi.user.model.User;
import com.turkmuhendisi.user.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthClient authClient;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthClient authClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authClient = authClient;
    }

    public boolean login(String email, String rawPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword())) {
            //return authClient.getJwt(email);
            return true;
        }
        throw new BadCredentialsException("Invalid credentials!");
    }
}
