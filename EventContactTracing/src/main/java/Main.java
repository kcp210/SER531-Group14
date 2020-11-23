import View.MainFrame;
import org.apache.jena.query.*;

public class Main {

    static String serviceEndpoint = "http://localhost:3030/TestDataset/query";

    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame("Event Contact Tracing Application");
    }

    public void queryTest() {
        String spaqlQuery = "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "\n" +
                "SELECT DISTINCT ?class ?label ?description\n" +
                "WHERE {\n" +
                "  ?class a owl:Class.\n" +
                "  OPTIONAL { ?class rdfs:label ?label}\n" +
                "  OPTIONAL { ?class rdfs:comment ?description}\n" +
                "}\n" +
                "LIMIT 25";


        QueryExecution q = QueryExecutionFactory.sparqlService(serviceEndpoint, spaqlQuery);
        ResultSet results = q.execSelect();
        ResultSetFormatter.out(System.out, results);
    }

}
