package com.semweb.model.ontologies;

public class Panier {
	int commande;
	String montant;
	public int getCommande() {
		return commande;
	}
	public void setCommande(int commande) {
		this.commande = commande;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	public Panier(int commande, String montant) {
		super();
		this.commande = commande;
		this.montant = montant;
	}
	
}