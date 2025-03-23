package com.turkmuhendisi.user.controller;

import com.turkmuhendisi.user.dto.LoginRequest;
import com.turkmuhendisi.user.dto.UserRegistrationDto;
import com.turkmuhendisi.user.dto.UserResponse;
import com.turkmuhendisi.user.model.User;
import com.turkmuhendisi.user.service.AuthService;
import com.turkmuhendisi.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final AuthClient authClient;

    public UserController(UserService userService, AuthService authService, AuthClient authClient) {
        this.userService = userService;
        this.authService = authService;
        this.authClient = authClient;
    }

    @PostMapping("/register")
    private void register(@RequestBody UserRegistrationDto user) {
        userService.registerUser(user);
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (userResponse != null) {
            String token = authClient.getJwt(userResponse.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email or password");
    }

    @GetMapping("/{email}")
    private ResponseEntity<UserResponse> findUserByEmail(@PathVariable String email) {
        UserResponse userResponse = userService.getUser(email);
        return ResponseEntity.ok(userResponse);
    }


}
