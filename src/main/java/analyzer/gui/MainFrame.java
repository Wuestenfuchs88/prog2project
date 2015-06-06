package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 700;

    public MainFrame(Data data) {

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Data Analyzer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MainPanel(data));
    }
}
