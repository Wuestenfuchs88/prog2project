package analyzer;

import analyzer.datastore.Data;
import analyzer.fileidentifier.LineOrientedReader;
import analyzer.fileidentifier.ReaderLoader;
import analyzer.fileidentifier.TabDelimitedReader;

import javax.swing.JFileChooser;
import java.io.File;


public class Analyzer {

    public static void main(String[] args) {

        ReaderLoader loader;
        Data data = null;

        JFileChooser fileChooser = new JFileChooser();
        int yayOrNay = fileChooser.showDialog(null, "Analyze File");

        String fileName;
        switch (yayOrNay) {
            case JFileChooser.APPROVE_OPTION:
                File file = fileChooser.getSelectedFile();
                fileName = file.getName();
                if (fileName.endsWith(".lin.txt")) {
                    loader = new LineOrientedReader();
                    data = loader.loadData(fileName);
                } else if (fileName.endsWith(".tab.txt")) {
                    loader = new TabDelimitedReader();
                    data = loader.loadData(fileName);
                } else System.out.println("Filename must end with .lin.txt or .tab.txt");
                break;
            case JFileChooser.CANCEL_OPTION:
                System.out.println("Open file dialog canceled");
                break;
        }


        /* TESTAUSGABE  (gibt Fehler wenn der opendfiledialog gecanceld wird)  */
        System.out.println("Number of Variables parsed: " + data.getNumberOfVariables());
        System.out.println("Name of file read: " + data.getFilename());
        for (int i = 0; i < data.getNumberOfVariables(); i++) {
            System.out.println("Content Variable " + i + ": " + data.getVariableContent().get(i).getVariableContent());
            System.out.println(data.getSortedValues(i));
            System.out.println(data.getReverseSortedValues(i));
        }
        System.out.println("*******  finito *******");

    }

}

