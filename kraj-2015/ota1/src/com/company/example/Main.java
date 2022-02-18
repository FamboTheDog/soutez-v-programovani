package com.company.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    private static final int width  = 1024;
    private static final int height = 768;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Prase");
        frame.setVisible(true);
        frame.setSize(new Dimension(width,height));
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        NormalDraw normal = new NormalDraw();
        frame.add(normal, BorderLayout.CENTER);

        Settings settings = new Settings(normal);
        frame.add(settings, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

}
