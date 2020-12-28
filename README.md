# IT4Commerce : Semantic web project
## Versions of used technologies 
- spring boot : 2.4.1
- Apache Maven : 3.6.3
- Java version: 15.0.1
- Apache jena fuseki : 3.16.0
- npm : 6.14.8
- Angular CLI version 11.0.4
## How to run project
- Run fuseki server : execute fuseki-server file
- Run spring boot application : ./mvnw spring-boot:run
- Run angular application : ng serve
## Building project
### Step 1 :Setup a triplestore 
- Create Dataset named "3a" in jena fuseki
- Define an ontolgie using Protégé and put it in the triplestore
- Generate Data of La-Poste agencies automatically from existing data source https://datanova.laposte.fr/explore/dataset/laposte_poincont2/download/?format=csv&timezone=Europe/Berlin&lang=fr&use_labels_for_header=true&csv_separator=%3B
  the java file used for this transformation is https://github.com/achrafBenHamou/SemWeb/blob/main/Back_end/semweb/src/main/java/com/semweb/dataprovider/CsvToTripleStore.java
  
### Step 2 : Setup of the Back-end
- Create Spring boot application as an internmediate between the Front-end and the fuseki triplestore
- Back-end acrhitecture is based on three packages and two files :
  - dataprovider package: contains the public data used in the project and the file used for the transformation
  - entrypoint package: contains files used for connection with the triplestore, as well same SparQL Queries 
  - ontologies package: contains files (ontologies) builded and generated with the Protégé software 
  - SemwebApplication.java : For running the back end, it contains the http requests
  - HelloController.java: contains http requests
  
### Step 3 : Semantic web Front-end application
#### Template of IT4Commerce 
- Template downloaded from https://github.com/malicksarrtwo/ecomerce-website
#### Using open street map
- We used leaflet for map
https://leafletjs.com/examples/quick-start/
- Create access token for using the map
https://account.mapbox.com/access-tokens/create

### More informations
for more informations you can read the project rapport https://drive.google.com/drive/folders/1J7oV2cakls4fKr9htEil-ccSMFGhQ7Yt?fbclid=IwAR1m4Cm0MxJ5yt3sUjZCJHHvxpqd0vNGW17c-ldqItjEneoUSIdAX4MR0To
