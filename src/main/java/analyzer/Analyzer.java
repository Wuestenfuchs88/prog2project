package analyzer;

import analyzer.datastore.Data;
import analyzer.fileidentifier.LineOrientedReader;
import analyzer.fileidentifier.ReaderLoader;
import analyzer.fileidentifier.TabDelimitedReader;
//import analyzer.fileidentifier.TabDelimitedReader;


public class Analyzer {

    public static void main(String[] args) {

//      Testweise - Ensprechende Files müssen dazu im Rootfolder des Projekts vorhanden sein

        ReaderLoader loader = null;

//        loader = new LineOrientedReader();
        loader = new TabDelimitedReader();

//        Data data = loader.loadData("reference-data.lin.txt");
        Data data = loader.loadData("reference-data.tab.txt");



        System.out.println("test");
        System.out.println();



    }
}

