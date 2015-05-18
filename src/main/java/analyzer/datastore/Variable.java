package analyzer.datastore;

import java.util.ArrayList;
import java.util.Collections;

public class Variable {

    private String variableName;
    private ArrayList<Double> variableContent;

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
}