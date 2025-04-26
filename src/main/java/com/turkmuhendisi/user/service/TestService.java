package com.turkmuhendisi.user.service;

import com.turkmuhendisi.user.controller.TestClient;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestClient testClient;

    public TestService(TestClient testClient) {
        this.testClient = testClient;
    }

    public String test() {
        return testClient.hi();
    }
}
