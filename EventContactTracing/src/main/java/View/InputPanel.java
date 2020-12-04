package View;

import Controller.Controller;

import javax.swing.*;

public class InputPanel extends JPanel {

    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton submitButton;

    public InputPanel() {
        nameLabel = new JLabel("Enter name: ");
        nameTextField = new JTextField("", 30);
        submitButton = new JButton("Submit");

        add(nameLabel);
        add(nameTextField);
        add(submitButton);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
}
