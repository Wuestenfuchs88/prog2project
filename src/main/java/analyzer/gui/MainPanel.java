package analyzer.gui;

import analyzer.datastore.Data;
import analyzer.fileidentifier.LineOrientedReader;
import analyzer.fileidentifier.ReaderLoader;
import analyzer.fileidentifier.TabDelimitedReader;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private final JLabel infoLabel;
    private Color color = Color.ORANGE;
    private Data data;
    private JComboBox variableTwoSelect;
    private JComboBox variableSelect;
    private JComboBox scaleDropdown;
    private int leftIndex = 0, rightIndex = 1;

    public MainPanel() {

        setLayout(new BorderLayout());

        //Center

        final JPanel centerPanel = new JPanel(new BorderLayout());

        final JSplitPane firstSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        final JSplitPane secondSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        firstSplit.setBottomComponent(secondSplit);
        firstSplit.setResizeWeight(0.5);
        secondSplit.setResizeWeight(0.5);


        final JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt & .lin.txt Files", "lin.txt", "txt");
        fileChooser.setFileFilter(filter);

        centerPanel.add(fileChooser, BorderLayout.CENTER);
        fileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent klick) {
                ReaderLoader loader = null;
                if (klick.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                    if (fileChooser.getSelectedFile().getName().endsWith(".lin.txt")) loader = new LineOrientedReader();
                    else if (fileChooser.getSelectedFile().getName().endsWith(".txt"))
                        loader = new TabDelimitedReader();

                    data = loader.loadData(fileChooser.getSelectedFile());
                    setInfoLabelText("showing " + data.getFilename() + "...");
                    for (int i = 0; i < data.getNumberOfVariables(); i++) {
                        variableSelect.addItem(data.getDataContent().get(i).getVariableName());
                        variableTwoSelect.addItem(data.getDataContent().get(i).getVariableName());
                        if (i >= 3) {
                            scaleDropdown.addItem(data.getDataContent().get(i).getVariableName());
                            scaleDropdown.setEnabled(true);
                        }
                    }
                    variableTwoSelect.setSelectedIndex(1);
                    firstSplit.setTopComponent(new ScatterPlot(data));
                    HistogramData leftHistogram = new HistogramData(data.getDataContent().get(leftIndex));
                    HistogramData rightHistogram = new HistogramData(data.getDataContent().get(rightIndex));
                    secondSplit.setLeftComponent(new Histogram(leftHistogram));
                    secondSplit.setRightComponent(new Histogram(rightHistogram));
                    centerPanel.remove(fileChooser);
                    centerPanel.add(firstSplit);
                    variableSelect.setEnabled(true);
                    variableTwoSelect.setEnabled(true);
                } else if (klick.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
                    JOptionPane.showMessageDialog(null, "Open file dialog canceled.", "Analyzer will close now..", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            }
        });

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

        scaleDropdown = new JComboBox();
        scaleDropdown.setEnabled(false);

        JPanel pointSizePanel = new JPanel(new GridLayout());
        pointSizePanel.setBackground(Color.LIGHT_GRAY);
        pointSizePanel.setBorder(BorderFactory.createTitledBorder((BorderFactory.createLineBorder(Color.LIGHT_GRAY)), "Set Point Size from Variable"));
        pointSizePanel.add(scaleDropdown);
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

        JLabel histoLabel = new JLabel("Choose variable");

        variableSelect = new JComboBox();
        variableSelect.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Left")); //setTitlePosition(TitledBorder.CENTER) ??
        variableSelect.setEnabled(false);
        variableTwoSelect = new JComboBox();
        variableTwoSelect.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Right")); //setTitlePosition(TitledBorder.CENTER) ??
        variableTwoSelect.setEnabled(false);

        histogramOptionsPanel.add(histoLabel);
        histogramOptionsPanel.add(new JLabel());
        histogramOptionsPanel.add(variableSelect);
        histogramOptionsPanel.add(variableTwoSelect);


        topPanel.add(infoLabel, BorderLayout.NORTH);
        graphOptionsPanelHolder.add(scatterPlotOptionsPanel);
        graphOptionsPanelHolder.add(histogramOptionsPanel);
        topPanel.add(graphOptionsPanelHolder, BorderLayout.CENTER);


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
        scaleDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //machwas
            }
        });

        variableSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftIndex = variableSelect.getSelectedIndex();
                HistogramData leftHistogram = new HistogramData(data.getDataContent().get(leftIndex));
                secondSplit.setLeftComponent(new Histogram(leftHistogram));
            }
        });

        variableTwoSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightIndex = variableTwoSelect.getSelectedIndex();
                HistogramData rightHistogram = new HistogramData(data.getDataContent().get(rightIndex));
                secondSplit.setRightComponent(new Histogram(rightHistogram));
            }
        });


    }

    public void setInfoLabelText(String text) {
        this.infoLabel.setText(text);
    }

}


