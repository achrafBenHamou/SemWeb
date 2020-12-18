package com.semweb.entrypoints;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.LiteralImpl;

//Written in 2015 by Thilo Planz 
//To the extent possible under law, I have dedicated all copyright and related and neighboring rights 
//to this software to the public domain worldwide. This software is distributed without any warranty. 
//http://creativecommons.org/publicdomain/zero/1.0/


import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

import com.semweb.model.ontologies.Agence;

import java.util.List;


public class Fuseki {

	public static void main(String[] argv){
		 String serviceURL = "http://localhost:3030/3a/";
		 //String query = "SELECT (count(*) AS ?count) { ?s ?p ?o }" ;
		    // See http://incubator.apache.org/jena/documentation/query/app_api.html
		 RDFConnection conn = RDFConnectionFactory.connect(serviceURL);
				 //conn.load("data.ttl") ;
		 String ville = "MARSEILLE";
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
		     System.out.println("Literal: "+agence.getLat()) ;
		 }
		 System.out.println("agences: "+agences) ;
		 qExec.close() ;
		 conn.close() ;
}
	}