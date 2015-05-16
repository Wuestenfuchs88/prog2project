package analyzer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import analyzer.datastore.Data;

/**
 * Created by Sven Sta on 15.05.2015.
 */
public class MainPanel extends JPanel {
    Data data = null;

    public MainPanel() {
        JLabel fileChooseDialog = new JLabel("Please choose one of the mentioned below buttons");
        JButton scatterplotButton = new JButton("Scatterplot");
        JButton barChartButton = new JButton("Bar Chart");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Design
        fileChooseDialog.setHorizontalTextPosition(JLabel.CENTER);
        fileChooseDialog.setVerticalTextPosition(JLabel.CENTER);
        scatterplotButton.setBackground(Color.lightGray);
        barChartButton.setBackground(Color.lightGray);
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 1;
        constraints.gridy = 0;

        //Addition

        add(fileChooseDialog, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(scatterplotButton, constraints);
        constraints.gridx = 3;
        constraints.gridy = 0;
        add(barChartButton, constraints);

        //Colour
        setBackground(Color.WHITE);


        //Buttons with ActionListener

        scatterplotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fileName = data.getFilename();
                if (fileName.endsWith(".lin.txt")) {
                    JFrame scatterplotFrame = new ScatterplotFrame();
                    scatterplotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    scatterplotFrame.setVisible(true);

                } else if (fileName.endsWith(".tab.txt")) {
                    JFrame scatterplotFrame = new ScatterplotFrame();
                    scatterplotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    scatterplotFrame.setVisible(true);
                } else {
                    System.err.println("Something went wrong!");
                }


            }
        });

        barChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fileName = data.getFilename();
                if (fileName.endsWith(".lin.txt")) {
                    //BarChartFrame barChartFrame = new BarChartFrame();
                } else if (fileName.endsWith(".tab.txt")) {
                    ///BarChartFrame barChartFrame = new BarChartFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect File");
                }

            }
        });


    }
}
