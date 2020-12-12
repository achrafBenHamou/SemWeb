package com.semweb.entrypoints;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;

//Written in 2015 by Thilo Planz 
//To the extent possible under law, I have dedicated all copyright and related and neighboring rights 
//to this software to the public domain worldwide. This software is distributed without any warranty. 
//http://creativecommons.org/publicdomain/zero/1.0/


import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;


public class Fuseki {


	public static void main(String[] argv){
		 String serviceURL = "http://localhost:3030/3a/";
		 String query = "SELECT (count(*) AS ?count) { ?s ?p ?o }" ;
		    // See http://incubator.apache.org/jena/documentation/query/app_api.html
		 RDFConnection conn = RDFConnectionFactory.connect(serviceURL);
				 //conn.load("data.ttl") ;
				 QueryExecution qExec = conn.query("SELECT DISTINCT ?s { ?s ?p ?o }") ;
				 ResultSet rs = qExec.execSelect() ;
				 while(rs.hasNext()) {
				     QuerySolution qs = rs.next() ;
				     Resource subject = qs.getResource("s") ;
				     System.out.println("Subject: "+subject) ;
				 }
				 qExec.close() ;
				 conn.close() ;
	}
}