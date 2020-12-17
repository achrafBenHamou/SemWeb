# Semantic web
# Semantic Web Back end 
## used versions 
spring boot : 2.4.1
Apache Maven : 3.6.3
Java version: 15.0.1
apache jena fuseki : 3.16.0

## first step
### setup a triplestore
- create Dataset named 3a in jena feseki
- Define a ontolgie using Protégé qnd put it in the triplestore
- Generate Data of postes agencies automatically from existing data sources
  https://datanova.laposte.fr/explore/dataset/laposte_poincont2/download/?format=csv&timezone=Europe/Berlin&lang=fr&use_labels_for_header=true&csv_separator=%3B
  
### Second step
- create Spring boot application for interaction with front end and fuseki triplestore
## Semantic web Front end application
### template of IT4Commerce 
- Template downloaded from https://github.com/malicksarrtwo/ecomerce-website
### using open street map
- we used leaflet for map
https://leafletjs.com/examples/quick-start/
- create access token for using the map
https://account.mapbox.com/access-tokens/create
