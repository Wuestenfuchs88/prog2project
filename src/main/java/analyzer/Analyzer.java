package analyzer;

import analyzer.gui.MainFrame;

import javax.swing.*;

public class Analyzer {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //   JFileChooser fc = new JFileChooser();
                //   fc.showOpenDialog(null);
                new MainFrame().setVisible(true);
            }
        });
    }
}

