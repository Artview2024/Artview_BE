package com.backend.Artview.global.abstractTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentConfig {

    @Bean
    public Parent parent() {
        return new Son();
    }
}
