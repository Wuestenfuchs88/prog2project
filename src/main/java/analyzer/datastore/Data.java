package analyzer.datastore;

import java.util.List;

public class Data {

    private final int numberOfVariables;
    private final List<Variable> variables;
    private final String fileName;

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
}

