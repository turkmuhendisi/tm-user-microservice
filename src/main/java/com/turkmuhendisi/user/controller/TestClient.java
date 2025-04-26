package com.turkmuhendisi.user.controller;

import com.turkmuhendisi.user.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "auth-service",
        url = "http://localhost:8081", // Eğer service discovery kullanmıyorsanız
        configuration = FeignConfig.class
)
public interface TestClient {
    @GetMapping("/test/hi")
    String hi();
}
