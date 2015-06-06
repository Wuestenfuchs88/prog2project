package analyzer;

import analyzer.datastore.Data;
import analyzer.fileidentifier.LineOrientedReader;
import analyzer.fileidentifier.ReaderLoader;
import analyzer.fileidentifier.TabDelimitedReader;
import analyzer.gui.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Analyzer {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                final JFileChooser fileChooser = new JFileChooser();
                final FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt & .lin.txt Files", "lin.txt", "txt");
                fileChooser.setFileFilter(filter);
                final int returnValue = fileChooser.showOpenDialog(null);
                ReaderLoader loader = null;
                switch (returnValue) {
                    case JFileChooser.APPROVE_OPTION:
                        if (fileChooser.getSelectedFile().getName().endsWith(".lin.txt"))
                            loader = new LineOrientedReader();
                        else if (fileChooser.getSelectedFile().getName().endsWith(".txt"))
                            loader = new TabDelimitedReader();

                        final Data data;
                        if (loader != null) {
                            data = loader.loadData(fileChooser.getSelectedFile());
                            new MainFrame(data).setVisible(true);
                        } else System.out.println("Analyzer encountered a problem while loading the selected file.");
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        JOptionPane.showMessageDialog(null, "Open file dialog canceled.", "Analyzer will close now..", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                }
            }
        });
    }
}

