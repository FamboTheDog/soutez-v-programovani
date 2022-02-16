package com.company.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.time.LocalTime;

public class Clock extends JPanel {

    private final int circleSize = 150;
    private final int circleCenter = (Main.getWidth() / 2) - (circleSize / 2);
    private final double lineCenter = (double) Main.getWidth() / 2;

    public Clock() {
        super();
        Timer timer = new Timer(300, e-> this.repaint());
        timer.setInitialDelay(0);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int hours = LocalTime.now().getHour();
        if (hours > 12) hours -= 12;

        int minutes = LocalTime.now().getMinute();

        int seconds = LocalTime.now().getSecond();

        Graphics2D gd = (Graphics2D) g;
        gd.drawOval(circleCenter, circleCenter, circleSize, circleSize);

        Line2D hour = new Line2D.Double(lineCenter ,lineCenter,lineCenter,lineCenter - ((double) circleSize / 3));
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(hours * 30),
                hour.getX1(),
                hour.getY1());
        gd.draw(at.createTransformedShape(hour));

        Line2D minute = new Line2D.Double(lineCenter ,lineCenter,lineCenter,lineCenter - ((double) circleSize / 2));
        at = AffineTransform.getRotateInstance(Math.toRadians(minutes * 6),
                minute.getX1(),
                minute.getY1());
        gd.draw(at.createTransformedShape(minute));

        gd.setColor(Color.red);
        Line2D second = new Line2D.Double(lineCenter ,lineCenter,lineCenter,lineCenter - ((double) circleSize / 2));
        at = AffineTransform.getRotateInstance(Math.toRadians(seconds * 6),
                second.getX1(),
                second.getY1());
        gd.draw(at.createTransformedShape(second));
    }


}
