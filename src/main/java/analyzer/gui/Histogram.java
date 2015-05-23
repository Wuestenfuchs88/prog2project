package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;

public class Histogram extends JPanel {

    private final Data data;

    private int index;
    private Color color = Color.ORANGE;

    public Histogram(Data data) {

        this.data = data;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), data.getDataContent().get(index).getVariableName()));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int barWidth = getWidth() / data.getDataContent().get(index).getVariableContent().size();
        int offset = (getWidth() - (barWidth * data.getDataContent().get(index).getVariableContent().size()));

        for (int i = 0; i < data.getDataContent().get(index).getVariableContent().size(); i++) {
            double value = data.getDataContent().get(index).getVariableContent().get(i);
            int barHeight = (int) ((getHeight() * value) / data.getDataContent().get(index).getMaxValue());

            int x = i * (barWidth);
            int y = getHeight() - barHeight;   // Stimmt das????????

            g.setColor(color);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);

        }
    }

}
