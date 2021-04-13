package com.covid.Nepal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NepalApplication {

	public static void main(String[] args) {

		SpringApplication.run(NepalApplication.class, args);

	}

}
