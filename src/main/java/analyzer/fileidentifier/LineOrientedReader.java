package analyzer.fileidentifier;

import analyzer.datastore.Data;
import analyzer.datastore.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LineOrientedReader implements ReaderLoader {


    @Override
    public Data loadData(File chosenFile) {

        String fileName = chosenFile.getName();
        Scanner lineOrientedScanner = null;

        try {
            lineOrientedScanner = new Scanner(chosenFile);
        } catch (FileNotFoundException e) {
            System.err.println("File not found or format is unknown!");
        }

        int numberOfVariables = Integer.parseInt(lineOrientedScanner.nextLine());

        List<Variable> variables = new ArrayList<>(numberOfVariables);

        for (int i = 0; i < numberOfVariables; i++) {
            String variableName = lineOrientedScanner.nextLine();
            Variable variable = new Variable(variableName);
            variables.add(variable);
        }

        String delimiter = lineOrientedScanner.nextLine();

        for (int i = 0; i < numberOfVariables; i++) {
            String[] values = lineOrientedScanner.nextLine().split(delimiter);
            for (int j = 0; j < values.length; j++) variables.get(i).addValue(Double.parseDouble(values[j]));
        }

        lineOrientedScanner.close();

        System.out.println("LineOrientedReader finished parsing file" + fileName);

        return new Data(numberOfVariables, variables, fileName);
    }
}
