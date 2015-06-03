package analyzer.gui;


import analyzer.datastore.Data;

import java.util.ArrayList;
import java.util.Collections;

public class HistogramData {

    private final Data data;
    private final int index;
    private final ArrayList<Integer> bins;
    private final int numberOfBins;


    public HistogramData(Data data, int index) {

        this.index = index;
        this.data = data;

        //bins
        //number of bins after Scott
        double binWidthScott = (3.49 * data.getDataContent().get(index).getSdtDev()) / Math.pow(data.getDataContent().get(index).getVariableContent().size(), (1 / 3));
        double numberOfBinsScott = Math.ceil((data.getDataContent().get(index).getRange()) / binWidthScott);

        //number if bins after Sturges' formula
        int numberOfBinsSturges = (int) Math.ceil((Math.log(data.getDataContent().get(index).getVariableContent().size() / Math.log(2))));
        //number of bins simple
        int numberOfBinsSimple = (int) Math.sqrt(data.getDataContent().get(index).getVariableContent().size());
        if (numberOfBinsSimple > 30) numberOfBinsSimple = 30;

        numberOfBins = numberOfBinsSturges;

        double classWidth;
        if (data.getDataContent().get(index).getMinValue() < 0) {
            classWidth = ((data.getDataContent().get(index).getMaxValue() + Math.abs(data.getDataContent().get(index).getMinValue())) / numberOfBins);
        } else {
            classWidth = (data.getDataContent().get(index).getRange() / numberOfBins);
        }

        bins = new ArrayList<>(numberOfBins);
        while (bins.size() <= numberOfBins) bins.add(0);
        for (int i = 0; i < data.getDataContent().get(index).getVariableContent().size(); i++) {

            if (data.getDataContent().get(index).getVariableContent().get(i) < 0) {
                double val = Math.abs(data.getDataContent().get(index).getVariableContent().get(i));
                val = val / classWidth;
                int value = bins.get((int) val);
                value++;
                bins.set((int) val, value);//umgedreht
            }
            int value = bins.get((int) (data.getDataContent().get(index).getVariableContent().get(i) / classWidth) - 1);
            value++;
            bins.set((int) (data.getDataContent().get(index).getVariableContent().get(i) / classWidth - 1), value);
        }
        for (int i = 0; i < bins.size(); i++) {
        }
    }

    public ArrayList getBins() {
        return bins;
    }

    public double getMaxValue() {
        return Collections.max(bins);
    }

    public String getVariableName() {
        return data.getDataContent().get(index).getVariableName();
    }

    public int geNumberOfValues() {
        return data.getDataContent().get(0).getVariableContent().size();
    }
}
