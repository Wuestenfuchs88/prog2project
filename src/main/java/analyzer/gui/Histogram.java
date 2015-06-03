package analyzer.gui;

import javax.swing.*;
import java.awt.*;

public class Histogram extends JPanel {

    private final HistogramData data;

    public Histogram(HistogramData data) {

        this.data = data;

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), data.getVariableName()));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < data.getBins().size(); i++) {


            int barWidth = getWidth() / data.getBins().size();
            double barHeight = ((int) data.getBins().get(i) / data.getMaxValue()) * getHeight();

            int x = (i) * (barWidth);
            int y = getHeight() - (int) barHeight;

            g.setColor(Color.GRAY);
            g.fillRect(x, y, barWidth, (int) barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, (int) barHeight);
        }
    }
}
