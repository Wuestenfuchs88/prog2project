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

    public Double getMaxValue() {
        return Collections.max(variableContent);
    }

    public Double getMinValue() {
        return Collections.min(variableContent);
    }

    public double getRange() {
        return getMaxValue() - getMinValue();
    }

}