package analyzer.gui;


import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;

public class DrawScatterPlot extends JPanel {

    private Data data;
    private Dimension size;
    private int width;
    private int height;

    public DrawScatterPlot(Data data, Dimension size) {
        this.data = data;
        this.size = size;

        width = (int) size.getWidth();
        height = (int) size.getHeight();

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int barWidth = width / data.getVariableContent().get(0).getVariableContent().size();
        for (int i = 0; i < data.getVariableContent().get(0).getVariableContent().size(); i++) {
            double value = data.getVariableContent().get(0).getVariableContent().get(i);
            int barHeight = (int) ((value / 100) * getHeight());

            int x = i * barWidth;
            int y = height - barHeight;

            g.setColor(Color.ORANGE);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.YELLOW);
            g.drawRect(x, y, barWidth, barHeight);

        }
        //  System.out.println(data.getVariableContent().get(0).getVariableContent().size());
        //  System.out.println(data.getVariableContent().get(0).getVariableContent());
        System.out.println(width);
        System.out.println(height);
    }
}
