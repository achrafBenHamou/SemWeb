package com.semweb.model.ontologies;

public class Client {
	Compte compte;
	String nom;
	String prenom;
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Client(Compte compte, String nom, String prenom) {
		super();
		this.compte = compte;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	
	
	
}