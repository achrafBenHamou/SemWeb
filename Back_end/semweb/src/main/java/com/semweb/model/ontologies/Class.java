package com.semweb.model.ontologies;

public class Class {
	int id;
	String nom;
	String adresse;
	String ville;
	double lagitude;
	public Class(int id, String nom, String adresse, String ville, double lagitude, double laltitude) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.ville = ville;
		this.lagitude = lagitude;
		this.laltitude = laltitude;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public double getLagitude() {
		return lagitude;
	}
	public void setLagitude(double lagitude) {
		this.lagitude = lagitude;
	}
	public double getLaltitude() {
		return laltitude;
	}
	public void setLaltitude(double laltitude) {
		this.laltitude = laltitude;
	}
	double laltitude;
}
