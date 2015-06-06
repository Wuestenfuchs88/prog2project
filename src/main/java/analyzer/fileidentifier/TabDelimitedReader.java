package analyzer.fileidentifier;

import analyzer.datastore.Data;
import analyzer.datastore.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TabDelimitedReader implements ReaderLoader {

    @Override
    public Data loadData(File chosenFile) {

        String fileName = chosenFile.getName();
        Scanner tabDelimitedScanner = null;

        try {
            tabDelimitedScanner = new Scanner(chosenFile);
        } catch (FileNotFoundException e) {
            System.err.println("File not found or format is unknown!");
        }

        String firstLine;
        int numberOfVariables = 0;
        List<Variable> variables = null;
        if (tabDelimitedScanner != null) {
            firstLine = tabDelimitedScanner.nextLine();

            numberOfVariables = firstLine.split("\\t").length;

            variables = new ArrayList<>(numberOfVariables);
            String[] variableName = firstLine.split("\\t");
            for (int i = 0; i < numberOfVariables; i++) {
                Variable variable = new Variable(variableName[i]);
                variables.add(variable);
            }

            while (tabDelimitedScanner.hasNextLine()) {
                String[] currentLine = tabDelimitedScanner.nextLine().split("\\t");
                for (int i = 0; i < numberOfVariables; i++)
                    variables.get(i).addValue(Double.parseDouble(currentLine[i]));
            }

            tabDelimitedScanner.close();
        } else System.out.println("Analyzer encountered a problem while parsing the selected file.");

        return new Data(numberOfVariables, variables, fileName);
    }
}
