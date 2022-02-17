package com.company.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.OptionalDouble;

public class Graph extends JPanel {

    private ArrayList<Integer> graphData;

    private final int width  = 500;
    private final int height = 300;

    private Color graphColor = Color.LIGHT_GRAY;
    private Color lineColor = Color.BLUE;

    public Graph(ArrayList<Integer> graphData) {
        this.graphData = graphData;

        this.setSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        double xMoveFactor = (double) width / graphData.size();
        gd.setColor(graphColor);
        gd.fillRect(0, 0, width, height);

        gd.setColor(lineColor);
        if (graphData.size() < 2) {
            gd.drawLine(0, height / 2, width, height / 2);
        } else {
            for (int i = 0; i < graphData.size() - 1; i++) {
                Line2D.Double line = new Line2D.Double((i+1) * xMoveFactor, height - graphData.get(i), (i+2) * xMoveFactor, height - graphData.get(i + 1));
                gd.draw(line);
            }
        }
    }

    public ArrayList<Integer> getGraphData() {
        return graphData;
    }

    public void setGraphData(ArrayList<Integer> graphData) {
        this.graphData = graphData;
        this.repaint();
    }

    public Color getGraphColor() {
        return graphColor;
    }

    public void setGraphColor(Color graphColor) {
        this.graphColor = graphColor;
        this.repaint();
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
        this.repaint();
    }
}
