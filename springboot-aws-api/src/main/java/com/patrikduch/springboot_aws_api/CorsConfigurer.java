package com.patrikduch.springboot_aws_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Custom configurer for Cross-origin resource sharing.
 * @author Patrik Duch
 */
@Configuration
public class CorsConfigurer implements WebMvcConfigurer {

    /**
     * CORS mapping for Springboot REST API service.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(
                "http://localhost:3000"
        );

    }
}