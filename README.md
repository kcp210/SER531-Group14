# SER531-Group14

## Semantic Web Contact Tracing Application

### Running Instructions:

- Clone the Github Repository and open SER531-Group14/EventContactTracing 

#### Start Fuseki Server on AWS

On Mac:

- Download putty with brew if not installed already: `brew install putty`
- Navigate to the EC2 - .pem file directory in the project. Be sure the 
permissions are set correctly
`chmod go-rw SED-fuseki.pem`
- SSH into the first EC2 instance machine
`ssh -i SED-fuseki.pem ubuntu@107.22.128.200`
- Start Fuseki server `cd apache-jena-fuseki-3.16.0/` then `java -jar fuseki-server.jar`
- Open endpoint to ensure the server is up: http://ec2-107-22-128-200.compute-1.amazonaws.com:3030/
- Open another terminal window, navigate to the EC2 - .pem file directory in the project,
 and SSH into the second EC2 instance machine:
`ssh -i fuseki2.pem ubuntu@54.144.150.242`
- Start Fuseki server `cd apache-jena-fuseki-3.16.0/` then `java -jar fuseki-server.jar`
- Open endpoint to ensure the server is up: http://ec2-54-144-150-242.compute-1.amazonaws.com:3030/ 
- To end the fuseki servers, type ctrl-c
- To exit the ssh: `exit`

#### Start Fuseki Server on Localhost

- Download the Apache Jena Fuseki server here: https://jena.apache.org/download/  
- Unzip the above download and unjar the fuseki server with `java -jar fuseki-server.jar`  
- Run the Fuseki server with `./fuseki-server`
- Open your web browser and navigate to http://localhost:3030/
- Upload the MedicalDetailsOntRDF.owl file from SER531-Group14/Ontologies_RDF to the server under dataset -> upload files,
 then hit upload now.
 
 #### Run the application
 
- Open the Event Contact tracing application in your IDE and run the main method in Main.java
 
- The application should output the three classes, MedicalCondition, Event and Person in the console.
 
 Note: If you have errors that your IDE cannot find Apache Jena, be sure you sync the pom.xml 
 file with your project.You can do this manually by running `mvn package` Alternatively, you
 can download the 3.16.0 Apache Jena jars from https://jena.apache.org/download/, unzip them, 
 then add the jars in the lib/ directory as dependencies in your project. In IntelliJ, you can 
 do this by going to File -> Project Structure -> Modules -> Dependencies and then clicking on 
 the plus button and add the path to the jars.


## Data Folder
This folder contains all the data in excel files and rules used
used to transform this data into ontologies.

## Data Generation
This folder contains the Python files used to generate our data.

## Ontologies
This folder contains the ontologies without any individual data

## Ontologies_RDF
This folder contains the ontologies with all the individual data




