package com.company.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class NormalDraw extends JPanel {

    private boolean slowMode = false;

    private int drawStage = 0;
    private int iteration = 1;

    Timer timer = new Timer(500, e-> {
        repaint();
        if (!slowMode) {
            drawStage++;
        } else {
            iteration++;
        }
    });

    public NormalDraw() {
        this.setSize(640, 480);
        this.setBackground(Color.BLACK);
        timer.start();
    }

    private final Line2D.Float[] lines = new Line2D.Float[]{
            // Front legs
            new Line2D.Float(110, 315, 160, 270),
            new Line2D.Float(160, 270, 210, 315),

            new Line2D.Float(160, 270, 480, 270),// Lower body

            // Back legs
            new Line2D.Float(480, 270, 530, 315),
            new Line2D.Float(480, 270, 430, 315),

            new Line2D.Float(480, 270, 480, 90), // Right body
            new Line2D.Float(480, 90, 530, 140), // Tail
            new Line2D.Float(480, 90, 160,90), // Upper body
            new Line2D.Float(160, 90, 160, 270), // Left Body
            new Line2D.Float(160, 270, 60, 180), // Lower head
            new Line2D.Float(60, 180, 160,90), // Upper head
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double ASPECT_RATIO = (double) 640 / 480;
        double heightScale = getHeight() == 480 ? 1 : (getHeight() * ASPECT_RATIO) / 480;
        Graphics2D gd = (Graphics2D) g;
        gd.scale((double) getWidth() / 640,  heightScale);

        if (!slowMode) {
            gd.setColor(Color.WHITE);
            for (int i = 0; i < drawStage && i < lines.length; i++) {
                gd.draw(lines[i]);
            }
        } else {
            gd.setColor(Color.RED);
            int i;
            for (i = 0; i < drawStage - 1 && i < lines.length; i++) {
                gd.draw(lines[i]);
            }

            slowDraw(gd, i);
        }

        if (drawStage > lines.length) gd.drawOval(135, 125, 10, 10);
        Toolkit.getDefaultToolkit().sync();
    }

    private void slowDraw(Graphics2D gd, int i) {
        if (i == lines.length) {
            return;
        }

        Line2D.Float currentLine = lines[i];
        float x = currentLine.x1 + (currentLine.x2 - currentLine.x1) / 100 * iteration;
        float y = currentLine.y1 + (currentLine.y2 - currentLine.y1) / 100 * iteration;

        gd.draw(new Line2D.Float(currentLine.x1, currentLine.y1, x, y));

        if (x == currentLine.x2 && y == currentLine.y2) {
            drawStage++;
            iteration = 1;
        }
    }

    public int getDrawStage() {
        return drawStage;
    }

    public void setDrawStage(int drawStage) {
        this.drawStage = drawStage;
    }

    public boolean isSlowMode() {
        return slowMode;
    }

    public void setSlowMode(boolean slowMode) {
        this.slowMode = slowMode;
        drawStage = 1;
        this.timer.stop();
        int SPEED_FACTOR = 20;
        this.timer.setDelay(SPEED_FACTOR);
        this.timer.start();
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
}
