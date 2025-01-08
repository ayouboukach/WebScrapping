package com.mahakim.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MahakimWebScrapingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MahakimWebScrapingApplication.class, args);
	}

}
