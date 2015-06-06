package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;

public class ScatterPlot extends JPanel {

    private final Data data;
    private Color color = Color.DARK_GRAY;
    private int xIndex, yIndex, zIndex, pointSize = 5;
    private boolean drawLines = false, pointWeight = false;

    public ScatterPlot(Data data, int xIndex, int yIndex, int zIndex) {

        this.data = data;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.zIndex = zIndex;
        setBackground(Color.WHITE);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xSize = pointSize;
        int ySize = pointSize;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);

        // x-axis
        g2.drawLine(10, getHeight() - 10, getWidth() - 75, getHeight() - 10);
        // y-axis
        g2.drawLine(10, getHeight() - 10, 10, 20);
        // x-axis arrow
        g2.drawLine(getWidth() - 75 - 5, getHeight() - 10 - 5, getWidth() - 75, getHeight() - 10);
        g2.drawLine(getWidth() - 75 - 5, getHeight() - 10 + 5, getWidth() - 75, getHeight() - 10);
        // y-axis arrow
        g2.drawLine(10 - 5, 20 + 5, 10, 20);
        g2.drawLine(10 + 5, 20 + 5, 10, 20);
        // draw origin Point
        g2.fillOval(7, getHeight() - 13, 6, 6);
        // draw labels
        g2.drawString(data.getDataContent().get(xIndex).getVariableName(), getWidth() - 70, getHeight() - 5);
        g2.drawString(data.getDataContent().get(yIndex).getVariableName(), 5, 10);

        for (int i = 0; i < data.getDataContent().get(xIndex).getVariableContent().size(); i++) {
            if (pointWeight) {
                xSize = ((int) (((data.getDataContent().get(zIndex).getVariableContent().get(i) -
                        data.getDataContent().get(zIndex).getMinValue())
                        / data.getDataContent().get(zIndex).getRange()) * pointSize));

                ySize = ((int) (((data.getDataContent().get(zIndex).getVariableContent().get(i) -
                        data.getDataContent().get(zIndex).getMinValue())
                        / data.getDataContent().get(zIndex).getRange()) * pointSize));
            }

            g2.fillOval(((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i) -
                    data.getDataContent().get(xIndex).getMinValue()) / data.getDataContent().get(xIndex).getRange())
                    * (getWidth() - getInsets().left)))) - (xSize / 2)
                    , ((int) (getHeight() - ((((data.getDataContent().get(yIndex).getVariableContent().get(i)
                    - data.getDataContent().get(yIndex).getMinValue())
                    / data.getDataContent().get(yIndex).getRange()) * (getHeight() - getInsets().top))))) - (ySize / 2)
                    , xSize, ySize);
        }

        if (drawLines) {
            for (int i = 0; i < data.getDataContent().get(xIndex).getVariableContent().size() - 1; i++) {
                g2.drawLine(((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i) -
                                data.getDataContent().get(xIndex).getMinValue())
                                / data.getDataContent().get(xIndex).getRange()) * (getWidth() - getInsets().left)))),
                        ((int) ((getHeight() - (((data.getDataContent().get(yIndex).getVariableContent().get(i)
                                - data.getDataContent().get(yIndex).getMinValue())
                                / data.getDataContent().get(yIndex).getRange()) * (getHeight() - getInsets().top))))),
                        ((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i + 1)
                                - data.getDataContent().get(xIndex).getMinValue())
                                / data.getDataContent().get(xIndex).getRange()) * (getWidth() - getInsets().left)))),
                        ((int) ((getHeight() - ((data.getDataContent().get(yIndex).getVariableContent().get(i + 1)
                                - data.getDataContent().get(yIndex).getMinValue())
                                / data.getDataContent().get(yIndex).getRange()) * (getHeight() - getInsets().top)))));
            }
        }
    }

    public void setXIndex(int xIndex) {
        this.xIndex = xIndex;
        repaint();
    }

    public void setYIndex(int yIndex) {
        this.yIndex = yIndex;
        repaint();
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
        repaint();
    }

    public void setDrawLines(boolean drawLines) {
        this.drawLines = drawLines;
        repaint();
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
        repaint();
    }

    public void setPointWeight(boolean pointWeight) {
        this.pointWeight = pointWeight;
        repaint();
    }
}
