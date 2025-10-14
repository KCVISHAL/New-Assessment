package com.assessment.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		System.out.println("Application Started");
		SpringApplication.run(TestApplication.class, args);
		System.out.println("Application Stopped");
	}

}
