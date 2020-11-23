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
        setSize(1000,1000);

        inputPanel = new InputPanel();
        resultsPanel = new ResultsPanel();

        resultsPanel.setPreferredSize(new Dimension(500, 500));

        getContentPane().add(inputPanel, BorderLayout.PAGE_START);
        getContentPane().add(resultsPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

}
