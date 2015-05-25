package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;

public class Histogram extends JPanel {

    private final Data data;

    private final int index;

    public Histogram(Data data, int index) {

        this.data = data;
        this.index = index;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), data.getDataContent().get(index).getVariableName()));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println(data.getDataContent().get(index).getSortedValues());

        //bins
        //width after Scott's normal reference rule
        int binWidthScott = (int) Math.ceil(((3.49 * data.getDataContent().get(index).getSdtDev()) / Math.pow(data.getDataContent().get(index).getVariableContent().size(), (1 / 3))));
        //number of bins after Scott ->> ???
        int numberOfBinsScott = (int) Math.ceil((data.getDataContent().get(index).getRange()) / binWidthScott);

        //number if bins after Sturges' formula
        int numberOfBinsSturges = (int) Math.ceil((Math.log(data.getDataContent().get(index).getVariableContent().size() / Math.log(2))));

        //number of bins simple
        int numberOfBinsSimple = (int) Math.sqrt(data.getDataContent().get(index).getVariableContent().size());

        int classWidth = (int) (data.getDataContent().get(index).getRange() / numberOfBinsSturges);

        System.out.println(classWidth);
        int barWidth = getWidth() / numberOfBinsSturges;
        int offset = (getWidth() - (barWidth * data.getDataContent().get(index).getVariableContent().size()));

        for (int i = 0; i < numberOfBinsSturges; i++) {
            int sum = 0;
            for (int j = 0; j < data.getDataContent().get(index).getVariableContent().size(); j++) {
                if (data.getDataContent().get(index).getVariableContent().get(j) <= i * classWidth + classWidth) {
                    sum += data.getDataContent().get(index).getVariableContent().get(j);
                }
            }
            System.out.println(sum);
            int value = (int) ((getHeight() * sum) / data.getDataContent().get(index).getSum());

            int barHeight = value / barWidth;

            int x = i * (barWidth);
            int y = getHeight() - barHeight;

            g.setColor(Color.GRAY);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
        }
        System.out.println("number of bins: " + numberOfBinsSturges);
        System.out.println("classwidth: " + classWidth);
        System.out.println("samplesize: " + data.getDataContent().get(index).getVariableContent().size());
    }

}
