package com.turkmuhendisi.user.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service", url = "http://localhost:8081")
public interface AuthClient {

    @PostMapping("/auth/token")
    String getJwt(@RequestParam("email") String email);
}
