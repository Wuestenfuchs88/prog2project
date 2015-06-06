package analyzer.gui;

import javax.swing.*;
import java.awt.*;

public class Histogram extends JPanel {

    private final HistogramData data;

    public Histogram(HistogramData data) {

        this.data = data;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(data.getVariableName()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < data.getBins().size(); i++) {

            int barWidth = (int) ((getWidth() - (getInsets().left * 1.5)) / data.getBins().size());
            int barHeight = (int) (((double) data.getBins().get(i).get() / (double) data.getMaxValue()) * (getHeight() - getInsets().top));

            int x = (i) * (barWidth) + getInsets().left;
            int y = getHeight() - barHeight - getInsets().bottom;

            g.setColor(Color.GRAY);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
        }
    }
}

