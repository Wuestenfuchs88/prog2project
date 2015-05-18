package analyzer;

import analyzer.gui.MainFrame;

import javax.swing.*;

public class Analyzer {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}

