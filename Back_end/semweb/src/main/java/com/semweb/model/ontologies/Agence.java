package com.semweb.model.ontologies;
///
public class Agence {
String name;
String ville;
double lat;
double lon;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getVille() {
	return ville;
}
public void setVille(String ville) {
	this.ville = ville;
}
public double getLat() {
	return lat;
}
public void setLat(double lat) {
	this.lat = lat;
}
public double getLon() {
	return lon;
}
public Agence(String name, String ville, double lat, double lon) {
	super();
	this.name = name;
	this.ville = ville;
	this.lat = lat;
	this.lon = lon;
}
public void setLon(double lon) {
	this.lon = lon;
}
}
