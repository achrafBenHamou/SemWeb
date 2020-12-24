# IT4Commerce : Semantic web project
## Versions of used technologies 
- spring boot : 2.4.1
- Apache Maven : 3.6.3
- Java version: 15.0.1
- Apache jena fuseki : 3.16.0
- npm : 6.14.8
- Angular CLI version 11.0.4
## How to run project
- run feseki server : execute fuseki-server file
- run spring boot application : ./mvnw spring-boot:run
- run angular application : ng serve
## Building project
### First step :Setup a triplestore 
- Create Dataset named 3a in jena feseki
- Define a ontolgie using Protégé and put it in the triplestore
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
