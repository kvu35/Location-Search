package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LocationSearchApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LocationSearchApplication.class, args);
	}
}