package com.company.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Settings extends JPanel {

    public Settings(Graph graph) {
        this.add(new JLabel("Velikost filtrace: "));

        this.setLayout(new BorderLayout());

        JPanel upperPart = new JPanel();
        this.add(upperPart, BorderLayout.NORTH);

        SpinnerModel model = new SpinnerNumberModel(0, 0, graph.getGraphData().size(), 1);
        JSpinner numberOfElementsToFilerChooser = new JSpinner(model);

        upperPart.add(numberOfElementsToFilerChooser);

        JButton startFiltration = new JButton("Začít filtraci");
        startFiltration.addActionListener(e-> {
            int numberOfElementsToFilter = (int) numberOfElementsToFilerChooser.getValue();

            if (numberOfElementsToFilter == 0) return;

            int filtrated = (int) graph.getGraphData()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .limit(numberOfElementsToFilter)
                    .average()
                    .orElse(0.0);
            ArrayList<Integer> filteredValues = new ArrayList<>();
            filteredValues.add(filtrated);
            graph.getGraphData()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .skip(numberOfElementsToFilter)
                    .forEach(filteredValues::add);

            graph.setGraphData(filteredValues);
            graph.repaint();
        });
        upperPart.add(startFiltration);

        JPanel lowerPart = new JPanel();
        this.add(lowerPart, BorderLayout.SOUTH);

        JButton backGroundColorChooser = new JButton("BC");
        backGroundColorChooser.addActionListener(e-> {
            Color initialColor = graph.getGraphColor();
            Color color = JColorChooser.showDialog(this,"Select a color",initialColor);
            graph.setGraphColor(color);
        });
        lowerPart.add(backGroundColorChooser);

        JButton lineColorChooser = new JButton("FC");
        lineColorChooser.addActionListener(e-> {
            Color initialColor = graph.getLineColor();
            Color color = JColorChooser.showDialog(this,"Select a color",initialColor);
            graph.setLineColor(color);
        });

        lowerPart.add(lineColorChooser);

        JButton save = new JButton("Uložit data");
        save.addActionListener(e-> {
            FileDialog fd = new FileDialog(Main.frame, "Choose a file", FileDialog.SAVE);
            fd.setDirectory("C:\\");
            fd.setFile("data.txt");
            fd.setVisible(true);
            String filename = fd.getFile();
            if (filename != null) {
                try {
                    File output = new File(filename);
                    output.createNewFile();
                    FileWriter writer = new FileWriter(output);
                    graph.getGraphData().forEach(x-> {
                        try {
                            writer.append(String.valueOf(x)).append(";\n");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        lowerPart.add(save);
    }

}
