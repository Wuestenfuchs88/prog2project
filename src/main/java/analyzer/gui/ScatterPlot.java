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
        setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xSize = pointSize;
        int ySize = pointSize;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final int X_AXIS_START_X = 10;
        final int X_AXIS_END_X = getWidth() - 80;
        final int X_AXIS_Y = getHeight() - 10;
        final int Y_AXIS_START_Y = getHeight() - 10;
        final int Y_AXIS_END_Y = 20;
        final int Y_AXIS_X = 10;
        final int ARROW_ARM = 5;
        final int ORIGIN_POINT_SIZE = 6;
        final int X_AXIS_LABEL_X = getWidth() - 75;
        final int X_AXIS_LABEL_Y = getHeight() - 5;
        final int Y_AXIS_LABEL_X = 5;
        final int Y_AXIS_LABEL_Y = 13;

        g2.setColor(Color.DARK_GRAY);
        // x-axis
        g2.drawLine(X_AXIS_START_X, X_AXIS_Y, X_AXIS_END_X, X_AXIS_Y);
        // y-axis
        g2.drawLine(Y_AXIS_X, Y_AXIS_START_Y, Y_AXIS_X, Y_AXIS_END_Y);
        // x-axis arrow
        g2.drawLine(X_AXIS_END_X - ARROW_ARM, X_AXIS_Y - ARROW_ARM, X_AXIS_END_X, X_AXIS_Y);
        g2.drawLine(X_AXIS_END_X - ARROW_ARM, X_AXIS_Y + ARROW_ARM, X_AXIS_END_X, X_AXIS_Y);
        // y-axis arrow
        g2.drawLine(Y_AXIS_X - ARROW_ARM, Y_AXIS_END_Y + ARROW_ARM, Y_AXIS_X, Y_AXIS_END_Y);
        g2.drawLine(Y_AXIS_X + ARROW_ARM, Y_AXIS_END_Y + ARROW_ARM, Y_AXIS_X, Y_AXIS_END_Y);
        // draw origin Point
        g2.fillOval(X_AXIS_START_X - (ORIGIN_POINT_SIZE / 2), X_AXIS_Y - (ORIGIN_POINT_SIZE / 2), ORIGIN_POINT_SIZE, ORIGIN_POINT_SIZE);
        // draw labels
        g2.drawString(data.getDataContent().get(xIndex).getVariableName(), X_AXIS_LABEL_X, X_AXIS_LABEL_Y);
        g2.drawString(data.getDataContent().get(yIndex).getVariableName(), Y_AXIS_LABEL_X, Y_AXIS_LABEL_Y);

        g2.setColor(color);
        for (int i = 0; i < data.getDataContent().get(xIndex).getVariableContent().size(); i++) {
            if (pointWeight) {
                xSize = ((int) (((data.getDataContent().get(zIndex).getVariableContent().get(i) -
                        data.getDataContent().get(zIndex).getMinValue())
                        / data.getDataContent().get(zIndex).getRange()) * pointSize));

                ySize = ((int) (((data.getDataContent().get(zIndex).getVariableContent().get(i) -
                        data.getDataContent().get(zIndex).getMinValue())
                        / data.getDataContent().get(zIndex).getRange()) * pointSize));
            }

            g2.fillOval(((((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i) -
                    data.getDataContent().get(xIndex).getMinValue()) / data.getDataContent().get(xIndex).getRange())
                    * (getWidth() - getInsets().right)))) - (xSize / 2)) + getInsets().left)
                    , (((((int) (getHeight() - ((((data.getDataContent().get(yIndex).getVariableContent().get(i)
                    - data.getDataContent().get(yIndex).getMinValue())
                    / data.getDataContent().get(yIndex).getRange()) * (getHeight() - getInsets().top))))) - (ySize / 2)) - getInsets().bottom))
                    , xSize, ySize);
        }

        if (drawLines) {
            for (int i = 0; i < data.getDataContent().get(xIndex).getVariableContent().size() - 1; i++) {
                g2.drawLine(((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i) -
                                data.getDataContent().get(xIndex).getMinValue())
                                / data.getDataContent().get(xIndex).getRange()) * (getWidth() - getInsets().right)))) + getInsets().left,
                        ((int) ((getHeight() - (((data.getDataContent().get(yIndex).getVariableContent().get(i)
                                - data.getDataContent().get(yIndex).getMinValue())
                                / data.getDataContent().get(yIndex).getRange()) * (getHeight() - getInsets().top))))) - getInsets().bottom,
                        ((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i + 1)
                                - data.getDataContent().get(xIndex).getMinValue())
                                / data.getDataContent().get(xIndex).getRange()) * (getWidth() - getInsets().right)))) + getInsets().left,
                        ((int) ((getHeight() - ((data.getDataContent().get(yIndex).getVariableContent().get(i + 1)
                                - data.getDataContent().get(yIndex).getMinValue())
                                / data.getDataContent().get(yIndex).getRange()) * (getHeight() - getInsets().top)))) - getInsets().bottom);
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
