package com.turkmuhendisi.user.controller;

import com.turkmuhendisi.user.dto.LoginRequest;
import com.turkmuhendisi.user.dto.UserRegistrationDto;
import com.turkmuhendisi.user.dto.UserResponse;
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

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    private HttpStatus register(@RequestBody UserRegistrationDto user) {
        userService.registerUser(user);
        return HttpStatus.OK;
    }

    @PostMapping("/login")
    private HttpStatus login(@RequestBody LoginRequest loginRequest) {
        //return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (authService.login(loginRequest.getEmail(), loginRequest.getPassword())) {
            return HttpStatus.OK;
        }
        return HttpStatus.UNAUTHORIZED;
    }

    @GetMapping("/{email}")
    private ResponseEntity<UserResponse> findUserByEmail(@PathVariable String email) {
        UserResponse userResponse = userService.getUser(email);
        return ResponseEntity.ok(userResponse);
    }


}
