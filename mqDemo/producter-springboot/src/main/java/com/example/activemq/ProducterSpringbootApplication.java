package com.example.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducterSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducterSpringbootApplication.class, args);
	}
}
