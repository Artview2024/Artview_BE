package com.backend.Artview.global.abstractTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Bean
    public StorageBase storageBase() {
        return new S3();
    }
}
