package com.company.example;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a basic clock
 */
public class Main {

    private static final int width  = 500;
    private static final int height = 500;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hodiny");
        frame.setVisible(true);
        frame.setSize(new Dimension(width,height));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Clock clock = new Clock();
        frame.add(clock, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static int getWidth() {
        return Main.width;
    }

    public static int getHeight() {
        return Main.height;
    }

}
