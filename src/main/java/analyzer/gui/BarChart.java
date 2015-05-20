package analyzer.gui;

import analyzer.datastore.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarChart extends JPanel {

    private final Data data;
    private final JLabel variable1;
    private final JComboBox dropdown;
    private final Panel optionsPanel;
    private String displayedVariable;
    private int index;
    private Color color = Color.ORANGE;

    public BarChart(Data data) {


        this.data = data;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        optionsPanel = new Panel();
        optionsPanel.setBackground(Color.LIGHT_GRAY);
        add(optionsPanel, BorderLayout.NORTH);

        displayedVariable = data.getVariableContent().get(index).getVariableName();

        variable1 = new JLabel("selected : " + displayedVariable);
        if (data.getVariableContent().size() >= 3) System.out.println("mehr als 2 variablen!");


        dropdown = new JComboBox();
        for (int i = 0; i < data.getNumberOfVariables(); i++) {
            dropdown.addItem(data.getVariableContent().get(i).getVariableName());
        }
        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayedVariable = (String) dropdown.getSelectedItem();
                index = dropdown.getSelectedIndex();
                setVariable(displayedVariable);
                repaint();
            }
        });

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};

        optionsPanel.setLayout(gbl);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        optionsPanel.add(dropdown, constraints);
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        optionsPanel.add(variable1, constraints);


        JRadioButton blue = new JRadioButton("Blue");
        JRadioButton red = new JRadioButton("Red");
        JRadioButton gray = new JRadioButton("Gray");
        JRadioButton green = new JRadioButton("Green");
        JRadioButton white = new JRadioButton("White");
        JRadioButton orange = new JRadioButton("Orange");
        orange.setSelected(true);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(blue);
        buttonGroup.add(red);
        buttonGroup.add(gray);
        buttonGroup.add(green);
        buttonGroup.add(white);
        buttonGroup.add(orange);

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
        Panel radioPanel = new Panel(new GridLayout(2, 3));
        radioPanel.add(blue);
        radioPanel.add(red);
        radioPanel.add(gray);
        radioPanel.add(green);
        radioPanel.add(white);
        radioPanel.add(orange);

        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 2;
        constraints.gridy = 0;
        optionsPanel.add(radioPanel, constraints);
    }

    public void setVariable(String text) {
        this.variable1.setText("selected : " + text);
    }

    @Override
    protected void paintComponent(Graphics g) {

        int barWidth = getWidth() / data.getVariableContent().get(index).getVariableContent().size();
        int offset = (getWidth() - (barWidth * data.getVariableContent().get(index).getVariableContent().size()));

        for (int i = 0; i < data.getVariableContent().get(index).getVariableContent().size(); i++) {
            double value = data.getVariableContent().get(index).getVariableContent().get(i);
            int barHeight = (int) ((getHeight() - optionsPanel.getHeight()) * value / data.getVariableContent().get(index).getMaxValue());

            int x = i * (barWidth);
            int y = getHeight() - barHeight;   // Stimmt das????????

            g.setColor(color);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
        }
    }

}
