package analyzer.gui;

import analyzer.datastore.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;


public class ScatterPlot extends JPanel {

    private final Data data;
    private int index;
    private Color color = Color.ORANGE;
    private double xAxis;
    private double yAxis;
    private double zeroXAxis;
    private double zeroYAxis;
    private double width;
    private double height;


    public ScatterPlot(Data data) {

        this.data = data;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), data.getDataContent().get(index).getVariableName()));
        this.xAxis = (getWidth() - getInsets().right);
        this.yAxis = getHeight() / data.getDataContent().get(index).getVariableContent().size() * data.getDataContent().get(index).getMaxValue();
        this.zeroXAxis = getInsets().left;
        this.zeroYAxis = getInsets().top;
        this.width = getWidth();
        this.height = getHeight();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        Line2D xLine = new Line2D.Double(zeroXAxis, zeroYAxis, xAxis, zeroYAxis);
        g2.draw(xLine);
        Line2D yLine = new Line2D.Double(zeroXAxis, zeroYAxis, zeroXAxis, yAxis);
        g2.draw(yLine);
        //g2.setStroke(new java.awt.BasicStroke(6, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND, 1.0f, new float[]{1}, 0.0f));


        double xSize = width / data.getDataContent().get(index).getVariableContent().size();
        double ySize = height / data.getDataContent().get(index).getVariableContent().size();
        g2.setPaint(Color.BLACK);
        for (int i = 0; i < data.getDataContent().size(); i++) {
            double value = data.getDataContent().get(index).getVariableContent().get(i);
            double x = (value + zeroXAxis);
            double y = (value + zeroYAxis);
            Ellipse2D ellipse = new Ellipse2D.Double(x, y, xSize, ySize);
            g2.fill(ellipse);
            g2.draw(ellipse);
            //int x = (int) (value / data.getDataContent().get(index).getMaxValue());
            //int y = (int) (value / data.getDataContent().get(index).getMaxValue());
            g2.setColor(Color.blue);
        }










    }
}
