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
    public Data loadData(File file) {

        String fileName = file.getName();
        Scanner tabDelimitedScanner = null;

        try {
            tabDelimitedScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found or format is unknown!");
        }

        String firstLine = tabDelimitedScanner.nextLine();
        int numberOfVariables = firstLine.split("\\t").length;

        List<Variable> variables = new ArrayList<>(numberOfVariables);
        String[] variableName = firstLine.split("\\t");
        for (int i = 0; i < numberOfVariables; i++) {
            Variable variable = new Variable(variableName[i]);
            variables.add(variable);
        }

        while (tabDelimitedScanner.hasNextLine()) {
            String[] currentLine = tabDelimitedScanner.nextLine().split("\\t");
            for (int i = 0; i < numberOfVariables; i++) variables.get(i).addValue(Double.parseDouble(currentLine[i]));
        }

        tabDelimitedScanner.close();

        System.out.println("TabDelimitedReader finished parsing file" + fileName);

        return new Data(numberOfVariables, variables, fileName);
    }
}
