package analyzer.gui;

import analyzer.datastore.Variable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HistogramData {

    private final Variable variable;
    private final ArrayList<AtomicInteger> bins;
    private final int maxValue;


    public HistogramData(Variable variable) {

        this.variable = variable;

        int numberOfBins = (int) Math.sqrt(variable.getVariableContent().size());
        if (numberOfBins > 50) numberOfBins = 50;


        bins = new ArrayList<>(numberOfBins);
        while (bins.size() < numberOfBins) bins.add(new AtomicInteger());

        ArrayList<Double> variableValues = variable.getVariableContent();
        for (Double value : variableValues) {

            double normValue = ((value - variable.getMinValue()) / variable.getRange());
            double normBinWidth = 1.0 / numberOfBins;

            int binIndex = (int) (normValue / normBinWidth);
            binIndex = Math.min(binIndex, numberOfBins - 1);

            bins.get(binIndex).incrementAndGet();
        }

        maxValue = Collections.max(bins, new Comparator<AtomicInteger>() {
            @Override
            public int compare(AtomicInteger o1, AtomicInteger o2) {
                return o1.get() - o2.get();
            }
        }).intValue();

    }

    public List<AtomicInteger> getBins() {
        return bins;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getVariableName() {
        return variable.getVariableName();
    }

}
