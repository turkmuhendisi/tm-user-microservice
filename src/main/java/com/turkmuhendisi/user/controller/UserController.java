package com.turkmuhendisi.user.controller;

import com.turkmuhendisi.user.dto.UserRegistrationDto;
import com.turkmuhendisi.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    private void register(@RequestBody UserRegistrationDto user) {
        userService.registerUser(user);
    }
}
