package kraj_2022.q3;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private static JLabel playerOneLabel;
    private static JLabel playerTwoLabel;
    private static String playerOneName;
    private static String playerTwoName;
    public GameField(String playerOneName, String playerTwoName, int value, int i) {
        playerOneLabel = new JLabel(playerOneName + " 0");
        playerTwoLabel = new JLabel(playerTwoName + " 0");
        GameField.playerOneName = playerOneName;
        GameField.playerTwoName = playerTwoName;
        BoardPanel boardPanel = new BoardPanel(this, playerOneName, playerTwoName, value, i);
        this.setLayout(new BorderLayout());
        this.add(playerOneLabel, BorderLayout.WEST);
        this.add(playerTwoLabel, BorderLayout.EAST);
        this.add(boardPanel, BorderLayout.CENTER);
    }

    public void setPlayerOneLabel(int num) {
        System.out.println(num);
        playerOneLabel.setText(playerOneName + " " + num);
        this.remove(playerOneLabel);
        this.add(playerOneLabel, BorderLayout.WEST);
    }

    public void setPlayerTwoLabel(int num) {
        System.out.println(num);
        playerTwoLabel.setText(playerTwoName + " " + num);
        this.remove(playerTwoLabel);
        this.add(playerTwoLabel, BorderLayout.EAST);
    }
}
