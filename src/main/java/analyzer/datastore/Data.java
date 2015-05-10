package analyzer.datastore;

import java.util.ArrayList;

public class Data {

    public Data(int numberOfVariables, ArrayList<String> variableNames, ArrayList<ArrayList<Double>> variableContent) {

        for (int i = 0; i < numberOfVariables; i++) {
            Variable variable = new Variable(variableNames.get(i));
        }


    }
}
