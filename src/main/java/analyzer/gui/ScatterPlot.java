package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;

public class ScatterPlot extends JPanel {

    private final Data data;

    public ScatterPlot(Data data) {

        this.data = data;

        setLayout(new BorderLayout());
        setBackground(Color.GREEN);

        Panel optionsPanel = new Panel();
        optionsPanel.setBackground(Color.LIGHT_GRAY);
        add(optionsPanel, BorderLayout.NORTH);


        JLabel variable1 = new JLabel("Variable 1: " + data.getVariableContent().get(0).getVariableName());
        JLabel variable2 = new JLabel("Variable 2: " + data.getVariableContent().get(1).getVariableName());
        JButton testbutton = new JButton("halloscatter!");

        optionsPanel.add(variable1);
        optionsPanel.add(variable2);
        optionsPanel.add(testbutton);

    }
}
