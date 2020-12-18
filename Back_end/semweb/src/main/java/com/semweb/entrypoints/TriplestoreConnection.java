package com.semweb.entrypoints;

import java.util.ArrayList;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.vocabulary.RDF;
import com.semweb.model.ontologies.Agence;

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

public static ArrayList<String> getAllAgences() {
	 String serviceURL = "http://localhost:3030/3a/";
	 //String query = "SELECT (count(*) AS ?count) { ?s ?p ?o }" ;
	    // See http://incubator.apache.org/jena/documentation/query/app_api.html
	 RDFConnection conn = RDFConnectionFactory.connect(serviceURL);
			 //conn.load("data.ttl") ;
	 
	 ArrayList<String> villes = new ArrayList<String>();
	 
			 QueryExecution qExec = conn.query("SELECT ?s ?p ?o where { ?s <http://somewhere/ville> ?o } limit 10") ;
			 ResultSet rs = qExec.execSelect() ;
			 while(rs.hasNext()) {
			     QuerySolution qs = rs.next() ;
			     Resource subject = qs.getResource("s") ;
			     Literal o = qs.getLiteral("o");
			     villes.add(o.toString());
			     System.out.println("Literal: "+o) ;
			 }
			 System.out.println("villes: "+villes) ;
			 qExec.close() ;
			 conn.close() ;
			 	return villes;
}

public static ArrayList<String> getAgence(String ville) {
	 String serviceURL = "http://localhost:3030/3a/";
	 //String query = "SELECT (count(*) AS ?count) { ?s ?p ?o }" ;
	    // See http://incubator.apache.org/jena/documentation/query/app_api.html
	 RDFConnection conn = RDFConnectionFactory.connect(serviceURL);
			 //conn.load("data.ttl") ;
	 
	 ArrayList<String> agences = new ArrayList<String>();
	 
			 QueryExecution qExec = conn.query("SELECT ?s ?p ?poste where { ?s <http://somewhere/ville> \""+ville+"\".\r\n" + 
			 		"      ?s <http://somewhere/poste_name> ?poste.\r\n" + 
			 		"} ") ;
			 ResultSet rs = qExec.execSelect() ;
			 while(rs.hasNext()) {
			     QuerySolution qs = rs.next() ;
			     Resource subject = qs.getResource("s") ;
			     Literal poste = qs.getLiteral("poste");
			     agences.add(poste.toString());
			     System.out.println("Literal: "+poste) ;
			 }
			 System.out.println("agences: "+agences) ;
			 qExec.close() ;
			 conn.close() ;
			 	return agences;
}

public static ArrayList<Agence> getAgencesLatLong(String ville) {
	 String serviceURL = "http://localhost:3030/3a/";
	 //String query = "SELECT (count(*) AS ?count) { ?s ?p ?o }" ;
	    // See http://incubator.apache.org/jena/documentation/query/app_api.html
	 RDFConnection conn = RDFConnectionFactory.connect(serviceURL);
			 //conn.load("data.ttl") ;	 
	 ArrayList<Agence> agences = new ArrayList<Agence>();
	 QueryExecution qExec = conn.query("SELECT ?s ?poste ?lat ?lon where { ?s <http://somewhere/ville> \""+ville+"\".\r\n" + 
		 		"  ?s <http://somewhere/poste_lat> ?lat.\r\n" + 
		 		"      ?s <http://somewhere/poste_name> ?poste.\r\n" + 
		 		"  		\r\n" + 
		 		"  		?s <http://somewhere/poste_lon> ?lon.\r\n" + 
		 		"} ") ;
			 ResultSet rs = qExec.execSelect() ;
			 while(rs.hasNext()) {
			     QuerySolution qs = rs.next() ;
			     Resource subject = qs.getResource("s") ;
			     Literal poste = qs.getLiteral("poste");
			     String s_poste = qs.getLiteral("poste").toString();
			     Literal lon = qs.getLiteral("lon") ;
			     Literal lat = qs.getLiteral("lat");	
			     String s_lon=lon.toString();
			     String s_lat=lat.toString();
			     //String str = "+123.4500d";
			     double d_lon = Double.parseDouble(s_lon);
			     double d_lat = Double.parseDouble(s_lat);
			     Agence agence = new Agence(s_poste,ville,d_lon,d_lat);
			     agences.add(agence);
			     System.out.println("Literal: "+agence) ;
			 }
			 System.out.println("agences: "+agences) ;
			 qExec.close() ;
			 conn.close() ;
			 	return agences;
}


}
