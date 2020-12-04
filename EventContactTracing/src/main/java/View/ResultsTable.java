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
        table = new JTable(0,4);

        // Set Headers
        JTableHeader header = table.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();
        columnModel.getColumn(0).setHeaderValue("First Name");
        columnModel.getColumn(1).setHeaderValue("Last Name");
        columnModel.getColumn(2).setHeaderValue("DOB");
        columnModel.getColumn(3).setHeaderValue("Contact");

        // Place in Scroll Pane on Panel
        pane = new JScrollPane(table);
        add(pane);
    }

    public void addRowOfData(Person person) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        String[] data = new String[4];
        data[0] = person.getfName();
        data[1] = person.getlName();
        data[2] = String.valueOf(person.getAge());
        data[3] = person.getContact();

        tableModel.addRow(data);
        //tableModel.fireTableDataChanged();
    }
}
