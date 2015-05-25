package analyzer.gui;

import analyzer.datastore.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
    private final int OFFSET;




    public ScatterPlot(Data data) {

        this.data = data;
        this.OFFSET = 80;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), data.getDataContent().get(0).getVariableName()));
        this.width = getWidth();
        this.height = getHeight();
        this.xAxis = data.getDataContent().get(0).getMaxValue();
        this.yAxis = data.getDataContent().get(1).getMaxValue();
        this.zeroXAxis = getInsets().left;
        this.zeroYAxis = getInsets().top;
        this.xCoordinates = new ArrayList<>();
        this.yCoordinates = new ArrayList<>();


        for (int i = 0; i < data.getDataContent().get(0).getVariableContent().size(); i++) {

            this.xValue = data.getDataContent().get(0).getVariableContent().get(i);
            xCoordinates.add(i, xValue);

        }
        for (int i = 0; i < data.getDataContent().get(1).getVariableContent().size(); i++) {
            this.yValue = data.getDataContent().get(1).getVariableContent().get(i);
            yCoordinates.add(i, yValue);

        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double xSize = 10;
        double ySize = 10;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //AffineTransform transform = new AffineTransform();
        //transform.setToRotation(Math.PI / 2);
        //g2.setTransform(transform);
        Line2D xLine = new Line2D.Double(zeroXAxis, zeroYAxis, xAxis, zeroYAxis);
        g2.draw(xLine);
        Line2D yLine = new Line2D.Double(zeroXAxis, zeroYAxis, zeroXAxis, yAxis);
        g2.draw(yLine);
        g2.setColor(Color.BLACK);
        //g2.setStroke(new java.awt.BasicStroke(6, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND, 1.0f, new float[]{1}, 0.0f));


        for (int i = 0; i < xCoordinates.size(); i++) {
            Ellipse2D ellipse = new Ellipse2D.Double(getxCoordinates(i), getyCoordinates(i), xSize, ySize);
            g2.fill(ellipse);
            g2.draw(ellipse);
            //int x = (int) (value / data.getDataContent().get(index).getMaxValue());
            //int y = (int) (value / data.getDataContent().get(index).getMaxValue());
            g2.setColor(Color.blue);
        }




    }

    public double getxCoordinates(int i) {
        {
            this.xCoordinate = xCoordinates.get(i);

        }
        return xCoordinate;

    }

    public double getyCoordinates(int i) {
        this.yCoordinate = yCoordinates.get(i);
        return yCoordinate;
    }


}
