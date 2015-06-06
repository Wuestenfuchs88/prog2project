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

        int numberOfVariables = 0;
        List<Variable> variables = null;
        if (lineOrientedScanner != null) {
            numberOfVariables = Integer.parseInt(lineOrientedScanner.nextLine());

            variables = new ArrayList<>(numberOfVariables);

            for (int i = 0; i < numberOfVariables; i++) {
                String variableName = lineOrientedScanner.nextLine();
                Variable variable = new Variable(variableName);
                variables.add(variable);
            }

            String delimiter = lineOrientedScanner.nextLine();

            for (int i = 0; i < numberOfVariables; i++) {
                String[] values = lineOrientedScanner.nextLine().split(delimiter);
                for (String value : values) variables.get(i).addValue(Double.parseDouble(value));
            }

            lineOrientedScanner.close();
        } else System.out.println("Analyzer encountered a problem while parsing the selected file.");

        return new Data(numberOfVariables, variables, fileName);
    }
}
