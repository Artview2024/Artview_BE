package com.backend.Artview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArtviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtviewApplication.class, args);
	}

}
