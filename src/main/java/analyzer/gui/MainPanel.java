package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainPanel extends JPanel {

    private final JComboBox<String> variableSelect, variableTwoSelect, variableThreeSelect;
    private Color color = Color.BLACK;
    private int xIndex = 0, yIndex = 1, zIndex = 0;
    private ScatterPlot scatterPlot;

    public MainPanel(final Data data) {

        setLayout(new BorderLayout());

        //Center
        final JPanel centerPanel = new JPanel(new BorderLayout());
        final JSplitPane firstSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        final JSplitPane secondSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        firstSplit.setBottomComponent(secondSplit);
        firstSplit.setResizeWeight(0.5);
        secondSplit.setResizeWeight(0.5);

        scatterPlot = new ScatterPlot(data, xIndex, yIndex, zIndex);
        firstSplit.setTopComponent(scatterPlot);
        HistogramData leftHistogram = new HistogramData(data.getDataContent().get(xIndex));
        HistogramData rightHistogram = new HistogramData(data.getDataContent().get(yIndex));
        secondSplit.setLeftComponent(new Histogram(leftHistogram));
        secondSplit.setRightComponent(new Histogram(rightHistogram));

        centerPanel.add(firstSplit);

        //Top

        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);

        final JPanel optionsPanelHolder = new JPanel(new GridLayout());

        JPanel visualsOptionsPanel = new JPanel(new GridLayout(1, 3));
        visualsOptionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), "Visuals"));
        visualsOptionsPanel.setBackground(Color.LIGHT_GRAY);

        final JSlider pointSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, 5);
        pointSizeSlider.setValue(5);
        pointSizeSlider.setMajorTickSpacing(10);
        pointSizeSlider.setMinorTickSpacing(1);
        pointSizeSlider.setPaintTicks(true);
        pointSizeSlider.setPaintLabels(true);
        pointSizeSlider.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Set Point Size Manually"));

        final JRadioButton orange = new JRadioButton("Orange");
        final JRadioButton red = new JRadioButton("Red");
        final JRadioButton gray = new JRadioButton("Gray");
        final JRadioButton green = new JRadioButton("Green");
        final JRadioButton black = new JRadioButton("Black");
        final JRadioButton blue = new JRadioButton("Blue");

        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(orange);
        buttonGroup.add(red);
        buttonGroup.add(gray);
        buttonGroup.add(green);
        buttonGroup.add(black);
        buttonGroup.add(blue);

        final JPanel radioPanel = new JPanel(new GridLayout(3, 2));
        radioPanel.add(orange);
        radioPanel.add(red);
        radioPanel.add(gray);
        radioPanel.add(green);
        radioPanel.add(black);
        radioPanel.add(blue);

        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Point Color"));
        radioPanel.setBackground(Color.LIGHT_GRAY);

        final JCheckBox toggleLines = new JCheckBox();
        toggleLines.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Draw Lines"));
        toggleLines.setBorderPainted(true);

        final JCheckBox pointSizeWeighted = new JCheckBox();
        pointSizeWeighted.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Scale Point Size"));
        pointSizeWeighted.setBorderPainted(true);
        pointSizeWeighted.setEnabled(false);

        final JPanel checkBoxPanel = new JPanel(new GridLayout(2, 1));
        checkBoxPanel.setBackground(Color.LIGHT_GRAY);
        checkBoxPanel.add(pointSizeWeighted);
        checkBoxPanel.add(toggleLines);

        visualsOptionsPanel.add(radioPanel);
        visualsOptionsPanel.add(pointSizeSlider);
        visualsOptionsPanel.add(checkBoxPanel);

        final JPanel variableOptionsPanel = new JPanel(new GridLayout(2, 2));
        variableOptionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), "Data Input"));
        variableOptionsPanel.setBackground(Color.LIGHT_GRAY);

        variableSelect = new JComboBox<>();
        variableSelect.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "X-Variable / Left Histogram"));
        variableTwoSelect = new JComboBox<>();
        variableTwoSelect.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Y-Variable / Right Histogram"));
        variableThreeSelect = new JComboBox<>();
        variableThreeSelect.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Z-Variable / Weight"));
        variableThreeSelect.setEnabled(false);


        for (int i = 0; i < data.getNumberOfVariables(); i++) {
            variableSelect.addItem(data.getDataContent().get(i).getVariableName());
            variableTwoSelect.addItem(data.getDataContent().get(i).getVariableName());
            if (i > 2) {
                variableThreeSelect.addItem(data.getDataContent().get(i).getVariableName());
                variableThreeSelect.setEnabled(true);
                pointSizeWeighted.setEnabled(true);
            }
        }
        variableTwoSelect.setSelectedIndex(1);

        variableOptionsPanel.add(variableSelect);
        variableOptionsPanel.add(variableThreeSelect);
        variableOptionsPanel.add(variableTwoSelect);

        optionsPanelHolder.add(variableOptionsPanel);
        optionsPanelHolder.add(visualsOptionsPanel);

        final JLabel infoLabel = new JLabel("Showing " + data.getFilename() + "...");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(infoLabel, BorderLayout.NORTH);
        topPanel.add(optionsPanelHolder, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);


        //ActionListeners
        pointSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                scatterPlot.setPointSize(pointSizeSlider.getValue());
            }
        });

        toggleLines.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                scatterPlot.setDrawLines(e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        pointSizeWeighted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                scatterPlot.setPointWeight(e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.BLUE;
                scatterPlot.setColor(color);
                repaint();
            }
        });

        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.RED;
                scatterPlot.setColor(color);
                repaint();
            }
        });

        gray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.LIGHT_GRAY;
                scatterPlot.setColor(color);
                repaint();
            }
        });

        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.GREEN;
                scatterPlot.setColor(color);
                repaint();
            }
        });

        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.BLACK;
                scatterPlot.setColor(color);
                repaint();
            }
        });

        orange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.ORANGE;
                scatterPlot.setColor(color);
                repaint();
            }
        });

        variableSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int setIndex = variableSelect.getSelectedIndex();
                HistogramData leftHistogram = new HistogramData(data.getDataContent().get(xIndex));
                secondSplit.setLeftComponent(new Histogram(leftHistogram));
                scatterPlot.setXIndex(setIndex);
            }
        });

        variableTwoSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int setIndex = variableTwoSelect.getSelectedIndex();
                HistogramData rightHistogram = new HistogramData(data.getDataContent().get(yIndex));
                secondSplit.setRightComponent(new Histogram(rightHistogram));
                scatterPlot.setYIndex(setIndex);
            }
        });

        variableThreeSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int setIndex = variableThreeSelect.getSelectedIndex();
                scatterPlot.setZIndex(setIndex);
            }
        });

    }
}


