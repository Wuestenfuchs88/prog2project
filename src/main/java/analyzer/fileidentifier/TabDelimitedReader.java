/* package analyzer.fileidentifier;

import analyzer.datastore.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TabDelimitedReader implements ReaderLoader {

    @Override
    public Data loadData(String fileName) {

        String variableName = null;
        double variableX = 0;
        Scanner tabDelimitedScanner = null;
        try {
            tabDelimitedScanner = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found or format is unknown!");
        }
        while (tabDelimitedScanner.hasNextLine()) {
            variableName = tabDelimitedScanner.nextLine();
            variableX = tabDelimitedScanner.nextDouble();
            tabDelimitedScanner.nextLine();
        }
        return new Data(variableName + "\n" + variableX);
    }
}
*/