import Controller.Controller;
import View.MainFrame;
import org.apache.jena.query.*;

public class Main {

    public static String serviceEndpoint = "http://localhost:3030/Medical";

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("Event Contact Tracing Application");
        Controller control = new Controller(mainFrame, serviceEndpoint);
    }

}
