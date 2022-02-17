package com.company.example;

import javax.swing.*;
import java.awt.*;

public class Graph extends JPanel {

    private GraphData graphData;

    public Graph(GraphData graphData) {
        this.graphData = graphData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;
    }
}
