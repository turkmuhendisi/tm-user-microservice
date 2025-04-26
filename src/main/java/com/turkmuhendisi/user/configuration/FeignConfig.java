package com.turkmuhendisi.user.configuration;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // Detaylı log için
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            // Gerekirse header ekleyin
            template.header("Content-Type", "application/json");
            template.header("Accept", "application/json");
        };
    }
}
