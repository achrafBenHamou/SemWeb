package com.semweb.model.ontologies;

public class Commande {
	int id;
	String date;
	String adresselivraison ;
	public Commande(String adresselivraison) {
		super();
		this.adresselivraison = adresselivraison;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAdresselivraison() {
		return adresselivraison;
	}
	public void setAdresselivraison(String adresselivraison) {
		this.adresselivraison = adresselivraison;
	}

	
}