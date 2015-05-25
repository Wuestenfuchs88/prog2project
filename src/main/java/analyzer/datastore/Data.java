package analyzer.datastore;

import java.util.List;

public class Data {

    private int numberOfVariables;
    private List<Variable> variables;
    private String fileName;

    public Data(int numberOfVariables, List<Variable> variables, String fileName) {
        this.numberOfVariables = numberOfVariables;
        this.variables = variables;
        this.fileName = fileName;
    }

    public Integer getNumberOfVariables() {
        return numberOfVariables;
    }

    public List<Variable> getDataContent() {
        return variables;
    }

    public String getFilename() {
        return this.fileName;
    }

/*  to delete

    public ArrayList<Double> getSortedValues(int index) {
        return variables.get(index).getSortedValues();
    }

    public ArrayList<Double> getReverseSortedValues(int index) {
        return variables.get(index).getReverseSortedValues();
    }*/

}

