package analyzer.gui;

import analyzer.datastore.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class ScatterPlot extends JPanel {

    private final Data data;
    private Color color = Color.ORANGE;
    private double xAxis;
    private double yAxis;
    private double zeroXAxis;
    private double zeroYAxis;
    private double width;
    private double height;
    private ArrayList<Double> xCoordinates;
    private ArrayList<Double> yCoordinates;
    private double xValue;
    private double yValue;
    private double xCoordinate;
    private double yCoordinate;





    public ScatterPlot(Data data) {

        this.data = data;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), data.getDataContent().get(0).getVariableName()));
        /*this.width = getWidth();
        this.height = getHeight();
        this.xAxis = data.getDataContent().get(0).getMaxValue();
        this.yAxis = data.getDataContent().get(1).getMaxValue();
        this.zeroXAxis = getHeight() - getInsets().bottom;
        this.zeroYAxis = getHeight() - getInsets().bottom;
        this.xCoordinates = new ArrayList<>();
        this.yCoordinates = new ArrayList<>();*/


        /*for (int i = 0; i < data.getDataContent().get(0).getVariableContent().size(); i++) {

            this.xValue = data.getDataContent().get(0).getVariableContent().get(i);
            xCoordinates.add(i, xValue);

        }
        for (int i = 0; i < data.getDataContent().get(1).getVariableContent().size(); i++) {
            this.yValue = data.getDataContent().get(1).getVariableContent().get(i);
            yCoordinates.add(i, yValue);

        }*/

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xSize = 10;
        int ySize = 10;
        final int OFFSETY = 260;
        final int OFFSETX = 590;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLUE);

        for (int i = 0; i < data.getDataContent().get(0).getVariableContent().size(); i++) {
            g2.fillOval(((int) ((((data.getDataContent().get(0).getVariableContent().get(i) -
                    data.getDataContent().get(0).getMinValue()) / data.getDataContent().get(0).getRange()) * OFFSETY) + (OFFSETX / 2)))
                    , ((int) (OFFSETY - (((data.getDataContent().get(1).getVariableContent().get(i) - data.getDataContent().get(1).getMinValue())
                    / data.getDataContent().get(1).getRange()) * OFFSETY) + (OFFSETY / 4)))
                    , xSize, ySize);

            System.out.println(getWidth());

        }

        for (int j = 0; j < data.getDataContent().get(0).getVariableContent().size() - 1; j++) {
            g2.drawLine(((int) ((((data.getDataContent().get(0).getVariableContent().get(j) -
                            data.getDataContent().get(0).getMinValue()) / data.getDataContent().get(0).getRange()) * OFFSETY) + (OFFSETX / 2))),
                    ((int) (OFFSETY - ((((data.getDataContent().get(1).getVariableContent().get(j) - data.getDataContent().get(1).getMinValue())
                            / data.getDataContent().get(1).getRange()) * OFFSETY) + (OFFSETY / 4)))),
                    ((int) ((((data.getDataContent().get(0).getVariableContent().get(j + 1)
                            - data.getDataContent().get(0).getMinValue()) / data.getDataContent().get(0).getRange()) * OFFSETY) + (OFFSETX / 2))),
                    ((int) ((((data.getDataContent().get(1).getVariableContent().get(j + 1) - data.getDataContent().get(1).getMinValue())
                            / data.getDataContent().get(1).getRange()) * OFFSETY) + (OFFSETY / 4))));

        }


    /*public double getxCoordinates(int i) {
        {
            this.xCoordinate = xCoordinates.get(i);

        }
        return xCoordinate;

    }

    public double getyCoordinates(int i) {
        this.yCoordinate = yCoordinates.get(i);
        yCoordinate = getHeight() - yCoordinate;
        return yCoordinate;
    }*/
        //public void setColor ()

    }
}
