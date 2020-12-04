package Controller;

import Model.Person;
import View.InputPanel;
import View.MainFrame;
import View.ResultsPanel;
import org.apache.jena.query.*;

import java.time.LocalDate;
import java.time.*;


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
        resultsPanel.clearAllTables();
        String firstname = inputPanel.getFirstName();
        String lastname = inputPanel.getLastName();
        ResultSet resultSet = getContactedPeople(firstname, lastname);
        while(resultSet.hasNext()) {
            QuerySolution row = resultSet.next();
            String first = String.valueOf(row.get("attendeeFirstName"));
            String last = String.valueOf(row.get("attendeeLastName"));
            String dob = String.valueOf(row.get("attendeeDob"));
            String phone = String.valueOf(row.get("attendeePhone"));
            String medicalCondition = String.valueOf(row.get("medCondName"));
            LocalDate actualDob = LocalDate.of( 1899 , Month.DECEMBER , 30 )
                    .plusDays((long) Double.parseDouble(dob));
            Person person = new Person(first, last, actualDob, phone, medicalCondition);
            resultsPanel.addPersonData(person);
        }

//        for (int i = 0; i < 30; i++) {
//            Person person = new Person("Bob","Jones", LocalDate.parse("7/14/86"),"602-418-4279", "None");
//            resultsPanel.addPersonData(person);
//        }

    }

    public String getDobString(LocalDate date) {
        return "";
    }

    public LocalDate getDobDate(String excelString) {
        return null;
    }


    public ResultSet getContactedPeople(String firstname, String lastname) {
//        String firstname = "Jim";
//        String lastname = "Johnson";

        String personId = getPersonId(firstname, lastname);
        System.out.println(personId);

        String query = "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "prefix med: <http://www.semanticweb.org/dragan/ontologies/2020/10/medical-ontology#>\n" +
                "prefix event: <http://www.semanticweb.org/dragan/ontologies/2020/10/event-ontology#>\n" +
                "prefix person: <http://www.semanticweb.org/dragan/ontologies/2020/10/person-ontology#>\n" +
                "\n" +
                "SELECT ?eventAttendeeId ?medCondName ?attendeePhone ?attendeeDob ?attendeeFirstName ?attendeeLastName{\n" +
                "  SERVICE <http://localhost:3030/Medical/sparql> {\n" +
                "    SELECT ?eventAttendeeId ?medCondName ?attendeePhone ?attendeeDob ?attendeeFirstName ?attendeeLastName\n" +
                "    WHERE {\n" +
                "      ?eventAttendeeUri a med:Person.\n" +
                "      ?eventAttendeeUri med:hasId ?eventAttendeeId.\n" +
                "      ?eventAttendeeUri med:hasMedicalCondition ?medCondUri.\n" +
                "      ?medCondUri med:hasMedicalName ?medCondName.\n" +
                "      {\n" +
                "        SERVICE <http://ec2-54-144-150-242.compute-1.amazonaws.com:3030/Person/query> {\n" +
                "          SELECT ?eventAttendeeId ?attendeePhone ?attendeeDob ?attendeeFirstName ?attendeeLastName WHERE {\n" +
                "            ?personUri a person:Person.\n" +
                "            ?personUri person:hasId ?eventAttendeeId.\n" +
                "            ?personUri person:hasPhoneNumber ?attendeePhone.\n" +
                "            ?personUri person:hasDateOfBirth ?attendeeDob.\n" +
                "            ?personUri person:hasFirstName ?attendeeFirstName.\n" +
                "            ?personUri person:hasLastName ?attendeeLastName.\n" +
                "            {\n" +
                "              SERVICE <http://ec2-107-22-128-200.compute-1.amazonaws.com:3030/Events/query> {\n" +
                "                SELECT DISTINCT ?eventAttendeeId WHERE {\n" +
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
        return results;
    }

    public String getPersonId(String firstname, String lastname) {
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
                "      ?infectedPersonUri person:hasId ?infectedPersonId.\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

        QueryExecution q = QueryExecutionFactory.sparqlService(serviceEndpoint, query);
        ResultSet results = q.execSelect();
        return String.valueOf(results.next().get("infectedPersonId"));
    }

}
