package ustredni_2019.q1;

import ustredni_2019.q1.boardtiles.BoardTile;
import ustredni_2019.q1.gson.templates.StartData;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private final InputData inputData;
    private StartData playerData;
    private int cellWidth;
    private int cellHeight;

    public GameBoard(InputData inputData) {
        this.inputData = inputData;
        System.out.println(inputData.getStart());
        this.playerData = inputData.getStart();
        playerData.setX(playerData.getX() - 1);
        playerData.setY(playerData.getY() - 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        cellWidth = this.getWidth() / inputData.getWidth();
        cellHeight = this.getHeight() / inputData.getHeight();

        drawBoard(gd);
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
        gd.fillOval((playerData.getX() * cellWidth) + cellWidth / 4, (playerData.getY() * cellHeight) + cellHeight / 4, cellWidth / 2, cellHeight / 2);
        gd.setColor(Color.BLUE);
        gd.drawOval((playerData.getX() * cellWidth) + cellWidth / 4, (playerData.getY() * cellHeight) + cellHeight / 4, cellWidth / 2, cellHeight / 2);
    }
}
