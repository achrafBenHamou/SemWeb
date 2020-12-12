# Semantic Web Back end 
## used versions 
- spring boot : 2.4.1
- Apache Maven : 3.6.3
- Java version: 15.0.1
- apache jena fuseki : 3.16.0

## first step
### setup a triplestore
- create Dataset named 3a in jena feseki
- Define a ontolgie using "Protégé" and put it in the triplestore
- Generate Data of postes agencies automatically from existing data sources
  https://datanova.laposte.fr/explore/dataset/laposte_poincont2/download/?format=csv&timezone=Europe/Berlin&lang=fr&use_labels_for_header=true&csv_separator=%3B
  
### Second step
- create Spring boot application for interaction with front end and fuseki triplestore
