package com.semweb.dataprovider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.vocabulary.RDF;

public class CsvToTripleStore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = ModelFactory.createDefaultModel();
		
	       String csvFile = "C:\\Users\\Achraf\\Desktop\\Gihub\\SemWeb\\Back_end\\semweb\\src\\main\\java\\com\\semweb\\dataprovider\\Data\\laposte_poincont2.csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ";";
	        
	    
	        String ex =  "http://www.it4commerce.com/";
	        String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#/";
	        String rdfs = "http://www.w3.org/2000/01/rdf-schema#" ;
	        String xsd =  "http://www.w3.org/2001/XMLSchema#" ;	        	        
	        String poste_URI = "http://somewhere/";
			Property poste_id = model.createProperty(ex+"poste_id");
			Property poste_adress = model.createProperty(ex+"poste_adress");
			Property poste_name = model.createProperty(ex+"poste_name");
			Property GeoSpacialLocation = model.createProperty(geo+"GeoSpacialLocation");
			Property poste_lat = model.createProperty(ex+"poste_lat");
			Property poste_lon = model.createProperty(ex+"poste_lon");
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] ligne = line.split(cvsSplitBy);
	                System.out.println(ligne[0]);
	                Resource poste = model.createResource(poste_URI + ligne[0])
							.addProperty(poste_id, ligne[0])
								.addProperty(poste_name, ligne[1])
								.addProperty(poste_adress, ligne[4])
									.addProperty(GeoSpacialLocation, 
									model.createResource(poste_URI + ligne[0])
									.addProperty(poste_lat, ligne[11])
									.addProperty(poste_lon, ligne[12]));
	                model.add(poste,RDF.type,GeoSpacialLocation) ;
	               
	                System.out.println("poste [poste_id= " + ligne[0] + " , poste_name=" + ligne[1] + " , poste_adresse=" + ligne[4] + " , poste_long=" + ligne[12]+" , poste_lat=" + ligne[12]+"]");
	                
	            }
      
	            String datasetURL = "http://localhost:3030";
	            String sparqlEndpoint = datasetURL + "/3a";
	            String sparqlUpdate = datasetURL + "/update";
	            String graphStore = datasetURL + "/data";
	            String serviceURL = "http://localhost:3030/3a/";
	            try (RDFConnection conn = RDFConnectionFactory.connect(serviceURL)) {
	                conn.put(model);
	            }
	            	          
	            model.write(System.out,"Turtle");
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	}

}