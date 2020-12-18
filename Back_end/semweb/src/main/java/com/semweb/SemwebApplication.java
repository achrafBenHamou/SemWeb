package com.semweb;
//
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semweb.entrypoints.TriplestoreConnection;
import com.semweb.entrypoints.TriplestoreConnection.*;

@SpringBootApplication
public class SemwebApplication {
	public static void main(String[] args) {
		SpringApplication.run(SemwebApplication.class, args);
	}


}
