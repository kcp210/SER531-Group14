# Event Contact Tracing

## To Run
Download the Apache Jena Fuseki server here: https://jena.apache.org/download/  

Unzip the above download and unjar the fuseki server with `java -jar fuseki-server.jar`  

Run the Fuseki server with `./fuseki-server`

Open your web browser and navigate to http://localhost:3030/

Upload the EventOnt.owl file from src/main/resources to the server under dataset -> upload files,
 then hit upload now.
 
Open the Event Contact tracing application in your IDE and run the main method in Main.java
 
 The application should output the three classes, MedicalCondition, Event and Person in the console.
 
 Note: If you have errors that your IDE cannot find Apache Jena, be sure you sync the pom.xml 
 file with your project.You can do this manually by running `mvn package` Alternatively, you
 can download the 3.16.0 Apache Jena jars from https://jena.apache.org/download/, unzip them, 
 then add the jars in the lib/ directory as dependencies in your project. In IntelliJ, you can 
 do this by going to File -> Project Structure -> Modules -> Dependencies and then clicking on 
 the plus button and add the path to the jars.