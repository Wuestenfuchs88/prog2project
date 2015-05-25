package analyzer.gui;

import analyzer.datastore.Data;
import analyzer.fileidentifier.LineOrientedReader;
import analyzer.fileidentifier.ReaderLoader;
import analyzer.fileidentifier.TabDelimitedReader;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private final JLabel infoLabel;
    private Color color = Color.ORANGE;
    private Data data;

    public MainPanel() {

        setLayout(new BorderLayout());

        //Top

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);

        String infoLabelText = "Welcome! Please choose a file to analyze";
        infoLabel = new JLabel(infoLabelText);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel graphOptionsPanelHolder = new JPanel(new GridLayout());

        JPanel scatterPlotOptionsPanel = new JPanel(new GridLayout(1, 3));
        scatterPlotOptionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), "Scatterplot"));
        scatterPlotOptionsPanel.setBackground(Color.LIGHT_GRAY);

        JSlider pointSize = new JSlider(JSlider.HORIZONTAL,
                0, 30, 5);

        pointSize.setMajorTickSpacing(10);
        pointSize.setMinorTickSpacing(1);
        pointSize.setPaintTicks(true);
        pointSize.setPaintLabels(true);
        pointSize.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Set pointsize manually"));

        JCheckBox pointSizeFromVariable = new JCheckBox();

        JComboBox dropdown = new JComboBox();
/*        for (int i = 0; i < data.getNumberOfVariables(); i++) {
            dropdown.addItem(data.getDataContent().get(i).getVariableName());
        }*/
        dropdown.addItem("Platzhalter");

        JPanel pointSizePanel = new JPanel(new GridLayout());
        pointSizePanel.setBackground(Color.LIGHT_GRAY);
        pointSizePanel.setBorder(BorderFactory.createTitledBorder((BorderFactory.createLineBorder(Color.LIGHT_GRAY)), "Set Point Size from Variable"));
        pointSizePanel.add(dropdown);
        pointSizePanel.add(pointSizeFromVariable);

        JRadioButton orange = new JRadioButton("Orange");
        orange.setSelected(true);
        JRadioButton red = new JRadioButton("Red");
        JRadioButton gray = new JRadioButton("Gray");
        JRadioButton green = new JRadioButton("Green");
        JRadioButton white = new JRadioButton("White");
        JRadioButton blue = new JRadioButton("Blue");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(orange);
        buttonGroup.add(red);
        buttonGroup.add(gray);
        buttonGroup.add(green);
        buttonGroup.add(white);
        buttonGroup.add(blue);

        JPanel radioPanel = new JPanel(new GridLayout(3, 2));
        radioPanel.add(orange);
        radioPanel.add(red);
        radioPanel.add(gray);
        radioPanel.add(green);
        radioPanel.add(white);
        radioPanel.add(blue);

        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), "Color"));
        radioPanel.setBackground(Color.LIGHT_GRAY);


        scatterPlotOptionsPanel.add(pointSizePanel);
        scatterPlotOptionsPanel.add(pointSize);
        scatterPlotOptionsPanel.add(radioPanel);


        JPanel histogramOptionsPanel = new JPanel(new GridLayout(2, 2));
        histogramOptionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), "Histogram"));
        histogramOptionsPanel.setBackground(Color.LIGHT_GRAY);

        JLabel histoLabel = new JLabel("Choose variable blablabla");
        JComboBox variableSelect = new JComboBox();
/*        for (int i = 0; i < data.getNumberOfVariables(); i++) {
            variableSelect.addItem(data.getDataContent().get(i).getVariableName());

        }*/
        variableSelect.addItem("Variable X");
        variableSelect.addItem("Variable Y");
        variableSelect.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Left")); //setTitlePosition(TitledBorder.CENTER) ??


        JComboBox variableTwoSelect = new JComboBox();
/*        for (int i = 0; i < data.getNumberOfVariables(); i++) {
            variableSelect.addItem(data.getDataContent().get(i).getVariableName());

        }*/
        variableTwoSelect.addItem("Variable XYZ");
        variableTwoSelect.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Right")); //setTitlePosition(TitledBorder.CENTER) ??

        histogramOptionsPanel.add(histoLabel);
        histogramOptionsPanel.add(new JLabel());
        histogramOptionsPanel.add(variableSelect);
        histogramOptionsPanel.add(variableTwoSelect);


        topPanel.add(infoLabel, BorderLayout.NORTH);
        graphOptionsPanelHolder.add(scatterPlotOptionsPanel);
        graphOptionsPanelHolder.add(histogramOptionsPanel);
        topPanel.add(graphOptionsPanelHolder, BorderLayout.CENTER);

        //Center

        final JPanel centerPanel = new JPanel(new BorderLayout());

        final JSplitPane firstSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        final JSplitPane secondSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        firstSplit.setBottomComponent(secondSplit);
        firstSplit.setResizeWeight(0.5);
        secondSplit.setResizeWeight(0.5);


        final JFileChooser fileChooser = new JFileChooser();
        centerPanel.add(fileChooser, BorderLayout.CENTER);
        fileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent klick) {
                ReaderLoader loader;
                if (klick.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                    if (fileChooser.getSelectedFile().getName().endsWith(".lin.txt")) {
                        loader = new LineOrientedReader();
                        data = loader.loadData(fileChooser.getSelectedFile());
                        setInfoLabelText("showing " + data.getFilename() + "...");
                        firstSplit.setTopComponent(new ScatterPlot(data));
                        secondSplit.setLeftComponent(new Histogram(data, 0));
                        secondSplit.setRightComponent(new Histogram(data, 1));
                        centerPanel.remove(fileChooser);
                        centerPanel.add(firstSplit);
                    } else if (fileChooser.getSelectedFile().getName().endsWith(".txt")) {
                        loader = new TabDelimitedReader();
                        data = loader.loadData(fileChooser.getSelectedFile());
                        setInfoLabelText("showing " + data.getFilename() + "...");
                        firstSplit.setTopComponent(new ScatterPlot(data));
                        secondSplit.setLeftComponent(new Histogram(data, 0));
                        secondSplit.setRightComponent(new Histogram(data, 1));
                        centerPanel.remove(fileChooser);
                        centerPanel.add(firstSplit);

                    } else {
                        setInfoLabelText("Filename must end with .lin.txt or .txt.");
                    }

                } else if (klick.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
                    JOptionPane.showMessageDialog(null, "Open file dialog canceled.", "Analyzer will close now..", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            }
        });


        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);


        //ActionListeners


        pointSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //set pointsize
            }
        });

        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.BLUE;
                repaint();
            }
        });
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.RED;
                repaint();
            }
        });
        gray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.LIGHT_GRAY;
                repaint();
            }
        });
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.GREEN;
                repaint();
            }
        });
        white.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.WHITE;
                repaint();
            }
        });
        orange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.ORANGE;
                repaint();
            }
        });

        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //machwas
            }
        });

    }

    public void setInfoLabelText(String text) {
        this.infoLabel.setText(text);
    }

}


