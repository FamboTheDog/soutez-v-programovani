package com.company.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    private static final int width  = 600;
    private static final int height = 600;

    private static BufferedReader reader;

    public static void main(String[] args ) throws IOException {
        JFrame frame = new JFrame("Hodiny");
        frame.setVisible(true);
        frame.setSize(new Dimension(width,height));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        loadFileFromResources("/Data.txt");
        ArrayList<Integer> graphData = loadDataFromFile();
        graphData.forEach(System.out::println);

        Graph graph = new Graph(new GraphData());
        frame.add(graph, BorderLayout.CENTER);

        Settings settings = new Settings();
        frame.add(settings, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private static ArrayList<Integer> loadDataFromFile() throws IOException {
        ArrayList<Integer> graphData = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null && !line.equals(";")) {
            graphData.add(Integer.parseInt(line.substring(0, line.length() - 2)));
        }
        return graphData;
    }

    private static void loadFileFromResources(String fileName) {
        InputStream inputStream = Main.class.getResourceAsStream(fileName);

        if (inputStream == null) {
            System.out.println("Soubor " + fileName + " nenalazen :(");
            return;
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        reader = new BufferedReader(streamReader);
    }

}
