package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Histogram extends JPanel {

    private final Data data;
    private final int index;

    public Histogram(Data data, int index) {

        this.data = data;
        this.index = index;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), data.getDataContent().get(index).getVariableName()));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //bins
        //number of bins after Scott
        double binWidthScott = (3.49 * data.getDataContent().get(index).getSdtDev()) / Math.pow(data.getDataContent().get(index).getVariableContent().size(), (1 / 3));
        double numberOfBinsScott = Math.ceil((data.getDataContent().get(index).getRange()) / binWidthScott);

        //number if bins after Sturges' formula
        int numberOfBinsSturges = (int) Math.ceil((Math.log(data.getDataContent().get(index).getVariableContent().size() / Math.log(2))));
        //number of bins simple
        int numberOfBinsSimple = (int) Math.sqrt(data.getDataContent().get(index).getVariableContent().size());
        if (numberOfBinsSimple > 30) numberOfBinsSimple = 30;

        int numberOfBins;
        numberOfBins = numberOfBinsSturges;

        double classWidth;
        if (data.getDataContent().get(index).getMinValue() < 0) {
            classWidth = ((data.getDataContent().get(index).getMaxValue() + Math.abs(data.getDataContent().get(index).getMinValue())) / numberOfBins);
        } else {
            classWidth = (data.getDataContent().get(index).getRange() / numberOfBins);
        }

        for (int i = 1; i < numberOfBins + 1; i++) {
            int bottom = 0;
            int top = 0;

            for (int j = 0; j < data.getDataContent().get(index).getVariableContent().size(); j++) {
                if (data.getDataContent().get(index).getSortedValues().get(j) >= (data.getDataContent().get(index).getMinValue() + ((i - 1) * classWidth))) {
                    bottom = j;
                    break;
                }
            }
            for (int j = 0; j < data.getDataContent().get(index).getVariableContent().size(); j++) {
                if (data.getDataContent().get(index).getSortedValues().get(j) >= (data.getDataContent().get(index).getMinValue() + (i * classWidth))) {
                    top = j;
                    break;
                }
            }

            List<Double> bin = data.getDataContent().get(index).getSortedValues().subList(bottom, top);

            double relativeFrequencyValue = (bin.size() / (double) data.getDataContent().get(index).getVariableContent().size());
            int barWidth = getWidth() / numberOfBins;

            int barHeight = (int) ((getHeight() / 100) * (relativeFrequencyValue * 100));


            int x = (i - 1) * (barWidth);
            int y = getHeight() - barHeight;

            g.setColor(Color.GRAY);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
//            System.out.println("bin " + i + " bootom index : " + bottom + " ---- top index : " + top);
//            System.out.println("bin " + i + " size = " + bin.size() + " equals " + relativeFrequencyValue + " %.");
        }
//        System.out.println("samplesize: " + data.getDataContent().get(index).getVariableContent().size());
//       System.out.println("number of bins: " + numberOfBinsSturges + "(Scott " + numberOfBinsScott + ")    (simple " + numberOfBinsSimple);
//        System.out.println("classwidth: " + classWidth);
//        System.out.println(data.getDataContent().get(index).getSortedValues());

    }
}
