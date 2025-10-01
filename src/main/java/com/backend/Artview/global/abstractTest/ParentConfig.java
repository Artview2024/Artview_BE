package com.backend.Artview.global.abstractTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentConfig {

    @Bean
    public StorageBase parent() {
        return new S3();
    }
}
