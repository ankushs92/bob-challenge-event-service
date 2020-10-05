package com.takeaway.eventservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeaway.eventservice.util.Json;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return Json.objectMapper;
    }
}
