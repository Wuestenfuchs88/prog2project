package analyzer;

import analyzer.datastore.Data;
import analyzer.fileidentifier.LineOrientedReader;
import analyzer.fileidentifier.ReaderLoader;
//import analyzer.fileidentifier.TabDelimitedReader;


public class Analyzer {

    public static void main(String[] args) {

//      Testweise

        ReaderLoader loader = null;

        loader = new LineOrientedReader();
//        loader = new TabDelimitedReader();

        Data data = loader.loadData("reference-data.lin.txt");


        System.out.println("test");
        System.out.println();



    }
}

