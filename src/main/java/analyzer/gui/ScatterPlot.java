package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;

public class ScatterPlot extends JPanel {

    private final Data data;

    public ScatterPlot(Data data) {

        this.data = data;
        setBackground(Color.WHITE);

        JLabel placeholder = new JLabel(data.getFilename());
        add(placeholder);

    }
}
