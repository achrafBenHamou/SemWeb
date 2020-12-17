# Semantic web
## Semantic Web Back end 
### Used versions 
- spring boot : 2.4.1
- Apache Maven : 3.6.3
- Java version: 15.0.1
- Apache jena fuseki : 3.16.0

### First step
#### Setup a triplestore
- Create Dataset named 3a in jena feseki
- Define a ontolgie using Protégé qnd put it in the triplestore
- Generate Data of postes agencies automatically from existing data sources
  https://datanova.laposte.fr/explore/dataset/laposte_poincont2/download/?format=csv&timezone=Europe/Berlin&lang=fr&use_labels_for_header=true&csv_separator=%3B
  
#### Second step
- Create Spring boot application for interaction with front end and fuseki triplestore
## Semantic web Front end application
### Template of IT4Commerce 
- Template downloaded from https://github.com/malicksarrtwo/ecomerce-website
### Using open street map
- We used leaflet for map
https://leafletjs.com/examples/quick-start/
- Create access token for using the map
https://account.mapbox.com/access-tokens/create
