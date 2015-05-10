package fileidentifier;

import analyzer.datastore.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LineOrientedReader implements ReaderLoader
{

    @Override
    public Data loadData(String fileName)
    {

        Scanner lineOrientedScanner = null;

        try {
            lineOrientedScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File not found or format is unknown!");
        }

        int numberOfVariables = Integer.parseInt(lineOrientedScanner.nextLine());

        ArrayList<String> variableNames = new ArrayList<>();
        for (int i = 0; i < numberOfVariables; i++) variableNames.add(i, lineOrientedScanner.nextLine());

        String delimiter = lineOrientedScanner.nextLine();

        ArrayList<ArrayList<Double>> variableContent = new ArrayList<>();
        for (int i = 0; i < numberOfVariables; i++) {
            variableContent.add(new ArrayList<Double>(i));
            String[] values = lineOrientedScanner.nextLine().split(delimiter);
            int iterations = values.length;
            for (int j = 0; j < iterations; j++) variableContent.get(i).add(j, Double.parseDouble(values[j]));
        }

        //TEST
        System.out.println("Anzahl Variabeln: " + numberOfVariables);
        System.out.println("Namen der Variabeln " + variableNames);
        System.out.println("Trennzeichen: " + delimiter);
        System.out.println("Inhalt Variable 1: " + variableContent.get(0));
        System.out.println("Inhalt Variable 2: " + variableContent.get(1));

        lineOrientedScanner.close();
        return new Data(numberOfVariables, variableNames, variableContent);
    }
}
