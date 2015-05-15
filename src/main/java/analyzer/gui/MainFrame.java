package analyzer.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sven Sta on 15.05.2015.
 */
public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 700;


    public MainFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Data Analyzer");
    }
}
