package analyzer.gui;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 700;

    public MainFrame() {

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Data Analyzer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MainPanel());


    }
}
