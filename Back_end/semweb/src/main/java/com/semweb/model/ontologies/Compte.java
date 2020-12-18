package com.semweb.model.ontologies;

public class Compte {
	int id;
	String email;
	String mdp;
	int tel;
	Panier panier ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public Panier getPanier() {
		return panier;
	}
	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	public Compte(int id, String email, String mdp, int tel, Panier panier) {
		super();
		this.id = id;
		this.email = email;
		this.mdp = mdp;
		this.tel = tel;
		this.panier = panier;
	}	
	
}

