package Controller;

import View.InputPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanelListener implements ActionListener {

    private InputPanel inputPanel;

    public InputPanelListener(InputPanel inputPanel) {
        this.inputPanel = inputPanel;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand() + " button clicked");
        System.out.println(inputPanel.getNameTextField().getText() + " is the name");
    }
}
