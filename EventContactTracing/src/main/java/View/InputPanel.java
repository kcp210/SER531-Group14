package View;

import Controller.InputPanelListener;

import javax.swing.*;

public class InputPanel extends JPanel {

    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton submitButton;

    public InputPanel() {
        nameLabel = new JLabel("Enter name: ");
        nameTextField = new JTextField("", 30);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new InputPanelListener(this));

        add(nameLabel);
        add(nameTextField);
        add(submitButton);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }
}
