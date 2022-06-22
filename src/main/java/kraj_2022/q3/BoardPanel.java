package kraj_2022.q3;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardPanel extends JPanel {

    private String playerOneName;
    private String playerTwoName;

    private char playerTwoSign;
    private char playerOneSign;

    private char[][] board;

    private int rounds;
    private int currentRound = 1;
    private int playerOneWins = 0;
    private int playerTwoWins = 0;
    private boolean isPlayerOneStarting = new Random().nextBoolean();
    private MoveListener moveListener = new MoveListener(this, isPlayerOneStarting);
    private GameField gameField;
    public BoardPanel(GameField gameField, String playerOneName, String playerTwoName, int value, int i) {
        this.gameField = gameField;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.rounds = i;
        board = new char[value][value];
        if (isPlayerOneStarting) {
            playerOneSign = 'X';
            playerTwoSign = 'O';
        } else {
            playerTwoSign = 'X';
            playerOneSign = 'O';
        }
        wipeBoard();
        this.addMouseListener(moveListener);
    }

    private void wipeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;
        gd.setColor(Color.BLACK);
        int cellWidth = getWidth() / board.length - 1;
        for (int i = 0; i <= board.length; i++) {
            gd.drawLine(i*cellWidth, 0, i*cellWidth, getHeight());
        }
        int cellHeight = getHeight() / board.length - 1;
        for (int i = 0; i <= board[0].length; i++) {
            gd.drawLine(0, i*cellHeight, getWidth(), i*cellHeight);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    gd.setColor(Color.BLUE);
                    gd.drawOval(j*cellWidth + 5, i * cellHeight + 5, cellWidth - 10, cellHeight - 10);
                } else if (board[i][j] == 'X') {
                    gd.setColor(Color.RED);
                    gd.drawLine(j*cellWidth, i * cellHeight, (j+1) * cellWidth, (i + 1) * cellHeight);
                    gd.drawLine((j+1)*cellWidth, i * cellHeight, j * cellWidth, (i + 1) * cellHeight);
                }
            }
        }
    }

    public void write(int x, int y, char toWrite) {
        double width = (double) this.getWidth() / board[0].length;
        double height = (double) this.getHeight() / board.length;
        int column = (int) (x / width);
        int row = (int) (y / height);
        if (isInBounds(row, column) && board[row][column] == ' ') board[row][column] = toWrite;
        isWin();
    }

    private boolean isInBounds(int row, int column) {
        return row >= 0 && column >= 0 && row < this.board.length && column < this.board[0].length;
    }

    private void isWin() {
        StringBuilder row = new StringBuilder();
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                row.append(chars[j]);
            }
            checkForWin(row);
            row = new StringBuilder();
        }
        StringBuilder col = new StringBuilder();
        for (int i = 0; i < board[0].length; i++) {
            for (char[] chars : board) {
                col.append(chars[i]);
            }
            checkForWin(col);
            col = new StringBuilder();
        }

        StringBuilder leftDiagonal = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <= i; j++) {
                leftDiagonal.append(board[i - j][j]);
            }
            checkForWin(leftDiagonal);
            leftDiagonal = new StringBuilder();
        }

        StringBuilder rightDiagonal = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = board[0].length - 1; j >= i; j--) {
                rightDiagonal.append(board[j][j - i]);
            }
            checkForWin(rightDiagonal);
            rightDiagonal = new StringBuilder();
        }
    }

    private void checkForWin(StringBuilder toCheck) {
        this.repaint();
        whoWon(toCheck.toString(), "XXXX");
        whoWon(toCheck.toString(), "OOOO");
    }

    private void whoWon(String toCheck, String looksFor) {
        if (toCheck.contains(looksFor)) {
            this.repaint();
            if (playerOneSign == looksFor.charAt(0)) {
                playerOneWins++;
                gameField.setPlayerOneLabel(playerOneWins);
                gameField.revalidate();
                gameField.repaint();
            } else {
                playerTwoWins++;
                gameField.setPlayerTwoLabel(playerTwoWins);
                gameField.revalidate();
                gameField.repaint();
            }
            if (currentRound == rounds) {
                String winMessage = "";
                if (playerOneWins > playerTwoWins) {
                    winMessage = playerOneName + " Vyhrál";
                } else {
                    winMessage = playerTwoName + " Vyhrál";
                }
                int popupOutput = JOptionPane.showOptionDialog(this, winMessage,"Výhra",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        null,
                        1);
                if (popupOutput == 0) {
                    System.exit(0);
                }
            } else {
                currentRound++;
                isPlayerOneStarting = !isPlayerOneStarting;
                moveListener.setPlayerOneMove(isPlayerOneStarting);
                wipeBoard();
            }
        }
    }
}
