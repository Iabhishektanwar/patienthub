package com.hospital.patienthub.patienthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PatienthubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatienthubApplication.class, args);
	}

}
