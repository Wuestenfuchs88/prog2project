package analyzer.fileidentifier;

import analyzer.datastore.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LineOrientedReader implements ReaderLoader {

    @Override
    public Data loadData(String fileName) {

        Scanner lineOrientedScanner = null;

        try {
            lineOrientedScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File not found or format is unknown!");
        }

        int numberOfVariables = lineOrientedScanner.nextInt();

        ArrayList<String> variableNames = new ArrayList<>();
        for (int i = 0; i <= numberOfVariables; i++) variableNames.add(i, lineOrientedScanner.nextLine());

        String delimiter = lineOrientedScanner.nextLine();

        ArrayList<ArrayList<Double>> variableContent = new ArrayList<>();
        for (int i = 0; i < numberOfVariables; i++) variableContent.add(new ArrayList<Double>());

        //Hier noch die 2 ArrayLists mit den Messwerten füllen

        //TEST
        System.out.println(numberOfVariables);
        System.out.println(variableNames);
        System.out.println(delimiter);
        System.out.println(variableContent);

        return new Data(numberOfVariables, variableNames);
    }
}
