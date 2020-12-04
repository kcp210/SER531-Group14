package View;

import javax.swing.*;

public class InputPanel extends JPanel {

    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JLabel dobLabel;
    private JTextField dobTextField;
    private JButton submitButton;

    public InputPanel() {
        firstNameLabel = new JLabel("Enter first name: ");
        firstNameTextField = new JTextField("", 15);
        lastNameLabel = new JLabel("Enter last name: ");
        lastNameTextField = new JTextField("", 15);
        dobLabel = new JLabel("Enter date of birth: ");
        dobTextField = new JTextField("", 10);
        submitButton = new JButton("Submit");

        add(firstNameLabel);
        add(firstNameTextField);
        add(lastNameLabel);
        add(lastNameTextField);
        add(dobLabel);
        add(dobTextField);
        add(submitButton);
    }

    public String getFirstName() {
        return firstNameTextField.getText();
    }

    public String getLastName() {
        return lastNameTextField.getText();
    }

    public String getDob() {
        return dobTextField.getText();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
}
