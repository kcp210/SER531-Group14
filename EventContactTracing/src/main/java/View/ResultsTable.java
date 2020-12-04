package View;

import Model.Person;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ResultsTable extends JPanel {

    private JTable table;
    private JScrollPane pane;

    public ResultsTable() {
        table = new JTable(0,5);

        // Set Headers
        JTableHeader header = table.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();
        columnModel.getColumn(0).setHeaderValue("First Name");
        columnModel.getColumn(1).setHeaderValue("Last Name");
        columnModel.getColumn(2).setHeaderValue("Age");
        columnModel.getColumn(3).setHeaderValue("Phone");
        columnModel.getColumn(4).setHeaderValue("Medical Condition");

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        columnModel.getColumn(2).setPreferredWidth(40);
        columnModel.getColumn(3).setPreferredWidth(130);
        columnModel.getColumn(4).setPreferredWidth(130);

        // Place in Scroll Pane on Panel
        pane = new JScrollPane(table);
        pane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane, BorderLayout.CENTER);

    }

    public void addRowOfData(Person person) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        String[] data = new String[5];
        data[0] = person.getfName();
        data[1] = person.getlName();
        data[2] = String.valueOf(person.getAge());
        data[3] = person.getContact();
        data[4] = person.getMedicalConditions();

        tableModel.addRow(data);
        tableModel.fireTableDataChanged();
    }

    public void clear() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        tableModel.fireTableDataChanged();
    }

}
