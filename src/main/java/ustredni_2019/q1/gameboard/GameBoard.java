package ustredni_2019.q1.gameboard;

import ustredni_2019.q1.boardtiles.BoardTile;
import ustredni_2019.q1.gson.templates.StartData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GameBoard extends JPanel {

    private final InputData inputData;
    private final StartData playerData;
    private int cellWidth;
    private int cellHeight;
    private final ImageIcon image;

    public GameBoard(InputData inputData) throws IOException {
        this.inputData = inputData;
        System.out.println(inputData.getStart());
        this.playerData = inputData.getStart();
        playerData.setX(playerData.getX() - 1);
        playerData.setY(playerData.getY() - 1);
        URL stream = getClass().getClassLoader().getResource("flag.png");
        assert stream != null;
        image = new ImageIcon(ImageIO.read(stream));

        GameBoardListener listener = new GameBoardListener(this);
        this.addKeyListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        cellWidth = this.getWidth() / inputData.getWidth();
        cellHeight = this.getHeight() / inputData.getHeight();

        drawBoard(gd);
        drawEnd(gd);
        drawPlayer(gd);
    }

    private void drawBoard(Graphics2D gd) {
        gd.setFont(new Font("courier new", Font.BOLD, cellHeight / 2));
        int x, y = 0;
        for (BoardTile[] boardTiles : inputData.getBoardPlan()) {
            x = 0;
            for (BoardTile boardTile : boardTiles) {
                boardTile.draw(gd, x, y, cellWidth, cellHeight);
                x += cellWidth;
            }
            y += cellHeight;
        }
    }

    private void drawPlayer(Graphics2D gd) {
        gd.setColor(Color.cyan);
        int x = (playerData.getX() * cellWidth) + cellWidth / 4;
        int y = (playerData.getY() * cellHeight) + cellHeight / 4;
        gd.fillOval(x, y, cellWidth / 2, cellHeight / 2);
        gd.setColor(Color.BLUE);
        gd.drawOval(x, y, cellWidth / 2, cellHeight / 2);
        drawEnergy(gd, x, y, cellWidth / 2, cellHeight / 2);
    }

    private void drawEnergy(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
        Font startFont = gd.getFont();
        gd.setFont(new Font("courier new", Font.BOLD, cellHeight / 3));
        FontMetrics metrics = gd.getFontMetrics(gd.getFont());
        int stringX = x + (cellWidth - metrics.stringWidth(Integer.toString(playerData.getE()))) / 2;
        int stringY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent();
        gd.drawString(Integer.toString(playerData.getE()), stringX, stringY);
        gd.setFont(startFont);
    }

    private void drawEnd(Graphics2D gd) {
        gd.drawImage(image.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH), ((inputData.getEnd().getX() - 1) * cellWidth) + cellWidth / 4, ((inputData.getEnd().getY() - 1) * cellHeight) + cellHeight / 4, cellWidth / 2, cellHeight / 2, this);
    }

    public void moveUp() {
        if (playerData.getY() > 0) {
            playerData.setY(playerData.getY() - 1);
            repaint();
        }
    }

    public void moveDown() {
        if (playerData.getY() < inputData.getHeight() - 1) {
            playerData.setY(playerData.getY() + 1);
            repaint();
        }
    }

    public void moveLeft() {
        if (playerData.getX() > 0) {
            playerData.setX(playerData.getX() - 1);
            repaint();
        }
    }

    public void moveRight() {
        if (playerData.getX() < inputData.getWidth() - 1) {
            playerData.setX(playerData.getX() + 1);
            repaint();
        }
    }
}
