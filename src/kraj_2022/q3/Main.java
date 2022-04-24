package kraj_2022.q3;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static NameChooser nameChooser = new NameChooser();
    private static JFrame frame = new JFrame("Piškvorky");

    public static void main(String[] args) {
        frame.setSize(new Dimension(640, 480));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        frame.add(nameChooser);

        frame.revalidate();
        frame.repaint();
    }

    public static void switchToGame(String playerOneName, String playerTwoName, int value, int i) {
        frame.remove(nameChooser);
        frame.add(new GameField(playerOneName, playerTwoName, value, i));
        frame.revalidate();
        frame.repaint();
    }
}
