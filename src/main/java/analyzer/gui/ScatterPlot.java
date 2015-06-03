package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;


public class ScatterPlot extends JPanel {

    private final Data data;
    private final int OFFSET;
    private Color color = Color.ORANGE;
    private double xAxis;
    private double yAxis;
    private double zeroXAxis;
    private double zeroYAxis;
    private double xValue;
    private double yValue;
    private double xCoordinate;
    private double yCoordinate;




    public ScatterPlot(Data data) {

        this.data = data;
        this.OFFSET = 80;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), data.getDataContent().get(0).getVariableName()));
/*
        this.xAxis = data.getDataContent().get(0).getMaxValue();
        this.yAxis = data.getDataContent().get(1).getMaxValue();
        this.zeroXAxis = getInsets().left;
        this.zeroYAxis = getInsets().top;

*/

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double xSize = 10;
        double ySize = 10;

        Graphics2D g2 = (Graphics2D) g;
/*
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
*/


        g2.setColor(color);
        for (int i = 0; i < data.getDataContent().get(0).getVariableContent().size(); i++) {
            Ellipse2D ellipse = new Ellipse2D.Double((data.getDataContent().get(0).getVariableContent().get(i)), (getHeight() - data.getDataContent().get(1).getVariableContent().get(i)), xSize, ySize);
            g2.fill(ellipse);
            g2.draw(ellipse);
            //int x = (int) (value / data.getDataContent().get(index).getMaxValue());
            //int y = (int) (value / data.getDataContent().get(index).getMaxValue());
        }




    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }


}
