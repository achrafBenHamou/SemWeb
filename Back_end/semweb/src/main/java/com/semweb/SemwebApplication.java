package com.semweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SemwebApplication {

	@RequestMapping("/getClients")
	public String getClients() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/getAgences")
	public String getAgences() {
		return "hello";
	}

}
