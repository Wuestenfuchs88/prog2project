package analyzer.gui;

import analyzer.datastore.Data;
import javax.swing.*;
import java.awt.*;


public class ScatterPlot extends JPanel {

    private final Data data;
    private Color color = Color.ORANGE;
    private int xIndex;
    private int yIndex;
    private int zIndex;
    private boolean drawLines = false;
    private int pointSize = 5;


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

        for (int i = 0; i < data.getDataContent().get(xIndex).getVariableContent().size(); i++) {
            if (data.getNumberOfVariables() > 2) {
                xSize = ((int) (((data.getDataContent().get(zIndex).getVariableContent().get(i) -
                        data.getDataContent().get(zIndex).getMinValue())
                        / data.getDataContent().get(zIndex).getRange()) * pointSize));

                ySize = ((int) (((data.getDataContent().get(zIndex).getVariableContent().get(i) -
                        data.getDataContent().get(zIndex).getMinValue())
                        / data.getDataContent().get(zIndex).getRange()) * pointSize));
            }

            g2.fillOval(((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i) -
                    data.getDataContent().get(xIndex).getMinValue()) / data.getDataContent().get(xIndex).getRange())
                    * (getWidth() - 2))))
                    , ((int) (getHeight() - ((((data.getDataContent().get(yIndex).getVariableContent().get(i)
                    - data.getDataContent().get(yIndex).getMinValue())
                    / data.getDataContent().get(yIndex).getRange()) * (getHeight() - 2)))))
                    , xSize, ySize);

        }

        if (drawLines == true) {
            for (int i = 0; i < data.getDataContent().get(xIndex).getVariableContent().size() - 1; i++) {
                g2.drawLine(((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i) -
                                data.getDataContent().get(xIndex).getMinValue()) / data.getDataContent().get(xIndex).getRange()) * (getWidth() - 2)))),
                        ((int) ((getHeight() - (((data.getDataContent().get(yIndex).getVariableContent().get(i) - data.getDataContent().get(yIndex).getMinValue())
                                / data.getDataContent().get(yIndex).getRange()) * (getHeight() - 2))))),
                        ((int) ((((data.getDataContent().get(xIndex).getVariableContent().get(i + 1)
                                - data.getDataContent().get(xIndex).getMinValue()) / data.getDataContent().get(xIndex).getRange()) * (getWidth() - 2)))),
                        ((int) ((getHeight() - ((data.getDataContent().get(yIndex).getVariableContent().get(i + 1) - data.getDataContent().get(yIndex).getMinValue())
                                / data.getDataContent().get(yIndex).getRange()) * (getHeight() - 2)))));
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
}
