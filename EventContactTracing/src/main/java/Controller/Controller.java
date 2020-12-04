package Controller;

import Model.Person;
import View.InputPanel;
import View.MainFrame;
import View.ResultsPanel;

import java.time.LocalDate;

public class Controller {

    private InputPanel inputPanel;
    private ResultsPanel resultsPanel;

    public Controller(MainFrame mainFrame) {
        this.inputPanel = mainFrame.getInputPanel();
        this.resultsPanel = mainFrame.getResultsPanel();

        inputPanel.getSubmitButton().addActionListener(e -> submit());
    }

    public void submit() {
//        for (int i = 0; i < 30; i++) {
//            Person person = new Person("Bob","Jones", LocalDate.parse("7/14/86"),"602-418-4279", "None");
//            resultsPanel.addPersonData(person);
//        }
    }
}
