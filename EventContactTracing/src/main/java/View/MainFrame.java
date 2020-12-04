package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private InputPanel inputPanel;
    private ResultsPanel resultsPanel;

    public MainFrame(String title) {
        super(title);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,1000);

        inputPanel = new InputPanel();
        resultsPanel = new ResultsPanel();

        resultsPanel.setPreferredSize(new Dimension(500, 900));

        getContentPane().add(inputPanel, BorderLayout.PAGE_START);
        getContentPane().add(resultsPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public InputPanel getInputPanel() {
        return inputPanel;
    }

    public ResultsPanel getResultsPanel() {
        return resultsPanel;
    }
}
