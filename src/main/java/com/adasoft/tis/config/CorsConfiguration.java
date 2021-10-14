package com.adasoft.tis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
    @Value("${CLIENT_ORIGIN}")
    private String clientOrigin;

    @Value("${DEV_ORIGIN}")
    private String devOrigin;

    @Bean
    public WebMvcConfigurer getCorsConfiguration() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins(clientOrigin, devOrigin)
                    .allowedHeaders("*")
                    .allowedMethods("*")
                    .allowCredentials(true)
                    .maxAge(3600);
                WebMvcConfigurer.super.addCorsMappings(registry);
            }
        };
    }
}
