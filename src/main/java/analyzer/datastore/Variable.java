package analyzer.datastore;

import java.util.ArrayList;
import java.util.Collections;

public class Variable {

    private final String variableName;
    private final ArrayList<Double> variableContent;

    public Variable(String variableName) {

        variableContent = new ArrayList<>();
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public ArrayList<Double> getVariableContent() {
        return variableContent;
    }

    public void addValue(Double value) {
        variableContent.add(value);
    }

    public ArrayList<Double> getSortedValues() {
        Collections.sort(variableContent);
        return variableContent;
    }

    public ArrayList<Double> getReverseSortedValues() {
        Collections.sort(variableContent);
        Collections.reverse(variableContent);
        return variableContent;
    }

    public Double getMaxValue() {
        return Collections.max(variableContent);
    }

    public Double getMinValue() {
        return Collections.min(variableContent);
    }

    public Double getSum() {
        double sum = 0;
        for (Double d : variableContent)
            sum += d;
        return sum;
    }

    public Double getSumIncNeg() {
        double sum = 0;
        for (Double d : variableContent) {
            if (d < 0) sum += Math.abs(d);
            sum += d;
        }
        return sum;
    }

    public Double getMean() {
        double sum = 0;
        for (Double d : variableContent)
            sum += d;
        return sum / variableContent.size();
    }

    public double getVariance() {
        double sum = 0;
        for (Double d : variableContent)
            sum += Math.pow((d - getMean()), 2);
        return sum / (variableContent.size());
    }

    public double getSdtDev() {
        return Math.sqrt(getVariance());
    }

    public double getRange() {
        return getMaxValue() - getMinValue();
    }

}