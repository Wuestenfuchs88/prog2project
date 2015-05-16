package analyzer.gui;

import javax.swing.*;

/**
 * Created by Sven Sta on 16.05.2015.
 */
public class ScatterplotFrame extends JFrame {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;

    public ScatterplotFrame() {

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Scatterplot");
        setLocation(0, 3);
        setVisible(true);
    }
}
