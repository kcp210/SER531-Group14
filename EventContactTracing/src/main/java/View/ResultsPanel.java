package View;

import Model.Person;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultsPanel extends JPanel {

    private JLabel immediateContactHighRiskLabel;
    private JLabel immediateContactMediumRiskLabel;
    private JLabel immediateContactLowRiskLabel;
    private JLabel secondaryContactHighRiskLabel;
    private JLabel secondaryContactMediumRiskLabel;
    private JLabel secondaryContactLowRiskLabel;

    private ResultsTable immediateContactHighRiskTable;
    private ResultsTable immediateContactMediumRiskTable;
    private ResultsTable immediateContactLowRiskTable;
    private ResultsTable secondaryContactHighRiskTable;
    private ResultsTable secondaryContactMediumRiskTable;
    private ResultsTable secondaryContactLowRiskTable;

    public ResultsPanel() {
        GridLayout layout = new GridLayout(12,1);
        setLayout(layout);
        setBorder(new EmptyBorder(0,0,20,0));

        immediateContactHighRiskLabel = new JLabel("Immediate Contact - High Risk");
        immediateContactMediumRiskLabel = new JLabel("Immediate Contact - Medium Risk");
        immediateContactLowRiskLabel = new JLabel("Immediate Contact - Low Risk");
        secondaryContactHighRiskLabel = new JLabel("Secondary Contact - High Risk");
        secondaryContactMediumRiskLabel = new JLabel("Secondary Contact - Medium Risk");
        secondaryContactLowRiskLabel = new JLabel("Secondary Contact - Low Risk");

        immediateContactHighRiskTable = new ResultsTable();
        immediateContactMediumRiskTable = new ResultsTable();
        immediateContactLowRiskTable = new ResultsTable();
        secondaryContactHighRiskTable = new ResultsTable();
        secondaryContactMediumRiskTable = new ResultsTable();
        secondaryContactLowRiskTable = new ResultsTable();

        add(immediateContactHighRiskLabel);
        add(immediateContactHighRiskTable);
        add(immediateContactMediumRiskLabel);
        add(immediateContactMediumRiskTable);
        add(immediateContactLowRiskLabel);
        add(immediateContactLowRiskTable);
        add(secondaryContactHighRiskLabel);
        add(secondaryContactHighRiskTable);
        add(secondaryContactMediumRiskLabel);
        add(secondaryContactMediumRiskTable);
        add(secondaryContactLowRiskLabel);
        add(secondaryContactLowRiskTable);
    }

    public void addPersonData(Person person) {
        int risk = 0;

        if (person.getAge() > 50)
            risk++;

        if(person.getMedicalConditions() != "None")
            risk++;

        if(risk == 2)
            immediateContactHighRiskTable.addRowOfData(person);
        else if (risk == 1)
            immediateContactMediumRiskTable.addRowOfData(person);
        else if (risk == 2)
            immediateContactLowRiskTable.addRowOfData(person);
    }
}
