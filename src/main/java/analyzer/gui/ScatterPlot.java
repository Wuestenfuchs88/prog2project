package analyzer.gui;

import analyzer.datastore.Data;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ScatterPlot extends JPanel {

    private final Data data;
    private Color color = Color.ORANGE;
    private ArrayList<Double> xCoordinates;
    private ArrayList<Double> yCoordinates;
    private double xValue;
    private double yValue;
    private double xCoordinate;
    private double yCoordinate;


    public ScatterPlot(Data data) {

        this.data = data;
        setBackground(Color.WHITE);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xSize = 3;
        int ySize = 3;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLUE);

        for (int i = 0; i < data.getDataContent().get(0).getVariableContent().size(); i++) {
            g2.fillOval(((int) ((((data.getDataContent().get(0).getVariableContent().get(i) -
                    data.getDataContent().get(0).getMinValue()) / data.getDataContent().get(0).getRange())
                    * (getWidth() - 2))))
                    , ((int) (getHeight() - ((((data.getDataContent().get(1).getVariableContent().get(i)
                    - data.getDataContent().get(1).getMinValue())
                    / data.getDataContent().get(1).getRange()) * (getHeight() - 2)))))
                    , xSize, ySize);

        }

        for (int j = 0; j < data.getDataContent().get(0).getVariableContent().size() - 1; j++) {
            g2.drawLine(((int) ((((data.getDataContent().get(0).getVariableContent().get(j) -
                            data.getDataContent().get(0).getMinValue()) / data.getDataContent().get(0).getRange()) * (getWidth() - 2)))),
                    ((int) ((getHeight() - (((data.getDataContent().get(1).getVariableContent().get(j) - data.getDataContent().get(1).getMinValue())
                            / data.getDataContent().get(1).getRange()) * (getHeight() - 2))))),
                    ((int) ((((data.getDataContent().get(0).getVariableContent().get(j + 1)
                            - data.getDataContent().get(0).getMinValue()) / data.getDataContent().get(0).getRange()) * (getWidth() - 2)))),
                    ((int) ((getHeight() - ((data.getDataContent().get(1).getVariableContent().get(j + 1) - data.getDataContent().get(1).getMinValue())
                            / data.getDataContent().get(1).getRange()) * (getHeight() - 2)))));
        }
    }

}
