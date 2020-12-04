package Controller;

import Model.Person;
import View.InputPanel;
import View.MainFrame;
import View.ResultsPanel;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

import java.time.LocalDate;

public class Controller {

    public String serviceEndpoint;

    private InputPanel inputPanel;
    private ResultsPanel resultsPanel;

    public Controller(MainFrame mainFrame, String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
        this.inputPanel = mainFrame.getInputPanel();
        this.resultsPanel = mainFrame.getResultsPanel();

        inputPanel.getSubmitButton().addActionListener(e -> submit());
    }

    public void submit() {
//        for (int i = 0; i < 30; i++) {
//            Person person = new Person("Bob","Jones", LocalDate.parse("7/14/86"),"602-418-4279", "None");
//            resultsPanel.addPersonData(person);
//        }
        getContactedPeople();
    }

    public void getContactedPeople() {
        String firstname = "Jim";
        String lastname = "Johnson";
        String dob = "25716";

        String personId = getPersonId(firstname, lastname, dob);
        System.out.println(personId);

        String query = "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "prefix med: <http://www.semanticweb.org/dragan/ontologies/2020/10/medical-ontology#>\n" +
                "prefix event: <http://www.semanticweb.org/dragan/ontologies/2020/10/event-ontology#>\n" +
                "prefix person: <http://www.semanticweb.org/dragan/ontologies/2020/10/person-ontology#>\n" +
                "\n" +
                "SELECT ?eventUri ?eventName ?eventAttendeeId ?medCondName ?attendeePhone ?attendeeDob ?attendeeFirstName ?attendeeLastName{\n" +
                "  SERVICE <http://localhost:3030/Medical/sparql> {\n" +
                "    SELECT ?eventUri ?eventName ?eventAttendeeId ?medCondName ?attendeePhone ?attendeeDob ?attendeeFirstName ?attendeeLastName\n" +
                "    WHERE {\n" +
                "      ?eventAttendeeUri a med:Person.\n" +
                "      ?eventAttendeeUri med:hasId ?eventAttendeeId.\n" +
                "      ?eventAttendeeUri med:hasMedicalCondition ?medCondUri.\n" +
                "      ?medCondUri med:hasMedicalName ?medCondName.\n" +
                "      {\n" +
                "        SERVICE <http://ec2-54-144-150-242.compute-1.amazonaws.com:3030/Person/query> {\n" +
                "          SELECT ?eventUri ?eventName ?eventAttendeeId ?attendeePhone ?attendeeDob ?attendeeFirstName ?attendeeLastName WHERE {\n" +
                "            ?personUri a person:Person.\n" +
                "            ?personUri person:hasId ?eventAttendeeId.\n" +
                "            ?personUri person:hasPhoneNumber ?attendeePhone.\n" +
                "            ?personUri person:hasDateOfBirth ?attendeeDob.\n" +
                "            ?personUri person:hasFirstName ?attendeeFirstName.\n" +
                "            ?personUri person:hasLastName ?attendeeLastName.\n" +
                "            {\n" +
                "              SERVICE <http://ec2-107-22-128-200.compute-1.amazonaws.com:3030/Events/query> {\n" +
                "                SELECT ?eventUri ?eventName ?eventAttendeeId WHERE {\n" +
                "                  ?infectedpersonUri a event:Person.\n" +
                "                  ?infectedpersonUri event:hasId \"" + personId + "\"^^xsd:string.\n" +
                "                  ?eventUri a event:Event.\n" +
                "                  ?eventUri event:hasEventName ?eventName.\n" +
                "                  ?eventUri event:hasAttendee ?infectedpersonUri.\n" +
                "                  ?eventUri event:hasAttendee ?eventAttendeeUri.\n" +
                "                  ?eventAttendeeUri event:hasId ?eventAttendeeId.\n" +
                "                  FILTER(?eventAttendeeUri != ?infectedpersonUri)\n" +
                "                }\n" +
                "                LIMIT 25\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "          LIMIT 25\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "    LIMIT 25\n" +
                "  }\t\n" +
                "}\n" +
                "LIMIT 25\n";


        QueryExecution q = QueryExecutionFactory.sparqlService(serviceEndpoint, query);
        ResultSet results = q.execSelect();
        ResultSetFormatter.out(System.out, results);
    }

    public String getPersonId(String firstname, String lastname, String dob) {
        String query = "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "prefix med: <http://www.semanticweb.org/dragan/ontologies/2020/10/medical-ontology#>\n" +
                "prefix event: <http://www.semanticweb.org/dragan/ontologies/2020/10/event-ontology#>\n" +
                "prefix person: <http://www.semanticweb.org/dragan/ontologies/2020/10/person-ontology#>\n" +
                "\n" +
                "SELECT ?infectedPersonId {\n" +
                "  SERVICE <http://ec2-54-144-150-242.compute-1.amazonaws.com:3030/Person/query> {\n" +
                "    SELECT ?infectedPersonId ?infectedPersonUri WHERE {\n" +
                "      ?infectedPersonUri a person:Person.\n" +
                "      ?infectedPersonUri person:hasFirstName \"" + firstname +"\".\n" +
                "      ?infectedPersonUri person:hasLastName \"" + lastname + "\".\n" +
                "      ?infectedPersonUri person:hasDateOfBirth \"" + dob + "\".\n" +
                "      ?infectedPersonUri person:hasId ?infectedPersonId.\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

        QueryExecution q = QueryExecutionFactory.sparqlService(serviceEndpoint, query);
        ResultSet results = q.execSelect();
        return String.valueOf(results.next().get("infectedPersonId"));
    }

}
