package analyzer.fileidentifier;

import analyzer.datastore.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TabDelimitedReader implements ReaderLoader {

    @Override
    public Data loadData(String fileName) {

        Scanner tabDelimitedScanner = null;

        try {
            tabDelimitedScanner = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.err.println("File not found or format is unknown!");
        }

        String firstLine = tabDelimitedScanner.nextLine();
        int numberOfVariables = firstLine.split("\\t").length;

        ArrayList<String> variableNames = new ArrayList<>();
        String[] variableName = firstLine.split("\\t");
        for (int i = 0; i < numberOfVariables; i++) variableNames.add(i, variableName[i]); //Frau Lüthy Fragen bzg. String & String[] -> auf eine Zeile.

        ArrayList<ArrayList<Double>> variableContent = new ArrayList<>();
        for (int i = 0; i < numberOfVariables; i++) variableContent.add(new ArrayList<Double>());

        while (tabDelimitedScanner.hasNextLine()) {
            String[] currentLine = tabDelimitedScanner.nextLine().split("\\t");
            for (int i = 0; i < numberOfVariables; i++) variableContent.get(i).add(Double.parseDouble(currentLine[i]));

        }


        //TEST
        System.out.println("Anzahl Variabeln: " + numberOfVariables);
        System.out.println("Namen der Variabeln: " + variableNames);
        System.out.println("Inhalt Variable 1: " + variableContent.get(0));
        System.out.println("Inhalt Variable 2: " + variableContent.get(1));

        tabDelimitedScanner.close();
        return new Data(numberOfVariables, variableNames, variableContent);
    }
}
