package com.semweb;

import org.springframework.web.bind.annotation.RestController;

import com.semweb.entrypoints.TriplestoreConnection;
import com.semweb.model.ontologies.Agence;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boooooooot!";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "helloooooooo";
	}


	@RequestMapping("/getClients")
	public String getClients() {
		return "clients!";
	}

	@RequestMapping("/getAgences")
	public ArrayList<String> getAgences() {
		System.out.print(TriplestoreConnection.getAllAgences());
		return TriplestoreConnection.getAllAgences();
	}
	
	@RequestMapping("/getPostes")
	public ArrayList<Agence> getPostes(@RequestParam String ville) {
		return TriplestoreConnection.getAgencesLatLong(ville);
	}
	
	
}