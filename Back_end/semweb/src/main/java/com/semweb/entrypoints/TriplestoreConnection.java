package com.semweb.entrypoints;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.vocabulary.RDF;
public class TriplestoreConnection {
    static String ex =  "http://www.it4commerce.com";
    static String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#/";
    static String rdfs = "http://www.w3.org/2000/01/rdf-schema#" ;
    String xsd =  "http://www.w3.org/2001/XMLSchema#" ;	        	        
    static String poste_URI = "http://somewhere/";
	
public static boolean putPosteData(String id,String name,String adress,String latitude,String longitude) {
    Model model = ModelFactory.createDefaultModel();
	Property poste_id = model.createProperty(ex+"/poste_id");
	Property poste_adress = model.createProperty(ex+"/poste_adress");
	Property poste_name = model.createProperty(ex+"/poste_name");
	Property GeoSpacialLocation = model.createProperty(geo+"GeoSpacialLocation");
	Property poste_lat = model.createProperty(ex+"/poste_lat");
	Property poste_lon = model.createProperty(ex+"/poste_lon");
    Resource poste = model.createResource(poste_URI + id)
			.addProperty(poste_id, id)
				.addProperty(poste_name, name)
				.addProperty(poste_adress, adress)
					.addProperty(GeoSpacialLocation, 
					model.createResource(poste_URI + id)
					.addProperty(poste_lat, latitude)
					.addProperty(poste_lon, longitude));
    model.add(poste,RDF.type,GeoSpacialLocation) ;
    return true;
}	

public static boolean putClientData(String id,String nom,String prenom) {
    Model model = ModelFactory.createDefaultModel();
	Property client_id = model.createProperty(ex+"#Client/id");
	Property client_nom = model.createProperty(ex+"nom");
	Property client_prenom = model.createProperty(ex+"prenom");
    Resource client = model.createResource(ex + id)
			.addProperty(client_id, id)
				.addProperty(client_nom, nom)
				.addProperty(client_prenom, prenom);
    model.add(client,RDF.type,ex) ;
    return true;
}

}
