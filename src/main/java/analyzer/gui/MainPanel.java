package analyzer.gui;

import analyzer.datastore.Data;
import analyzer.fileidentifier.LineOrientedReader;
import analyzer.fileidentifier.ReaderLoader;
import analyzer.fileidentifier.TabDelimitedReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainPanel extends JPanel {

    private Panel centerPanel;
    private JLabel infoLabel;
    private JButton scatterPlotButton;
    private JButton barChartButton;
    private JFileChooser fileChooser;
    private Data data;
    private ScatterPlot scatterPlotPanel;
    private BarChart barChartPanel;

    public MainPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        centerPanel = new Panel();
        Panel topPanel = new Panel();
        Panel labelPanel = new Panel();
        Panel buttonsPanel = new Panel();

        topPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new BorderLayout());

        labelPanel.setBackground(Color.LIGHT_GRAY);
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        String infoLabelText = "Welcome! Please choose a file to analyze";
        infoLabel = new JLabel(infoLabelText);
        labelPanel.add(infoLabel);

        scatterPlotButton = new JButton("ScatterPlot");
        barChartButton = new JButton("Bar Chart");
        scatterPlotButton.setEnabled(false);
        barChartButton.setEnabled(false);
        buttonsPanel.add(scatterPlotButton);
        buttonsPanel.add(barChartButton);

        scatterPlotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                barChartPanel.setVisible(false);
                scatterPlotPanel.setVisible(true);
                setInfoLabelText("showing scatter plot of " + data.getFilename() + "...");
                System.out.println("klick...");
            }
        });

        barChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                scatterPlotPanel.setVisible(false);
                barChartPanel.setVisible(true);
                setInfoLabelText("showing bar chart of " + data.getFilename() + "...");
            }
        });


        topPanel.add(labelPanel, BorderLayout.NORTH);
        topPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
        centerPanel.add(fileChooser, BorderLayout.CENTER);
        fileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent klick) {
                ReaderLoader loader;
                String fileName;
                if (klick.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                    File chosenFile = fileChooser.getSelectedFile();
                    fileName = chosenFile.getName();
                    if (fileName.endsWith(".lin.txt")) {
                        loader = new LineOrientedReader();
                        data = loader.loadData(fileChooser.getSelectedFile());
                        fileChooser.setVisible(false);
                        scatterPlotButton.setEnabled(true);
                        barChartButton.setEnabled(true);
                        setInfoLabelText("Great! Please choose visualization");
                        barChartPanel = new BarChart(data);
                        scatterPlotPanel = new ScatterPlot(data);
                        centerPanel.add(scatterPlotPanel);
                        centerPanel.add(barChartPanel);
                        scatterPlotPanel.setVisible(false);
                        barChartPanel.setVisible(false);

                    } else if (fileName.endsWith(".txt")) {
                        loader = new TabDelimitedReader();
                        data = loader.loadData(fileChooser.getSelectedFile());
                        fileChooser.setVisible(false);
                        scatterPlotButton.setEnabled(true);
                        barChartButton.setEnabled(true);
                        setInfoLabelText("Great! Please choose visualization");
                        barChartPanel = new BarChart(data);
                        scatterPlotPanel = new ScatterPlot(data);
                        centerPanel.add(scatterPlotPanel);
                        centerPanel.add(barChartPanel);
                        scatterPlotPanel.setVisible(false);
                        barChartPanel.setVisible(false);
                    } else setInfoLabelText("Filename must end with .lin.txt or .txt.");
                } else if (klick.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
                    JOptionPane.showMessageDialog(null, "Open file dialog canceled.", "Analyzer will close now..", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            }
        });

    }

    public void setInfoLabelText(String text) {
        this.infoLabel.setText(text);
    }

}


