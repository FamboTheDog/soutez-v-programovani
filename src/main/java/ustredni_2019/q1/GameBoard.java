package ustredni_2019.q1;

import ustredni_2019.q1.boardtiles.BoardTile;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {

    private final InputData inputData;

    public GameBoard(InputData inputData) {
        this.inputData = inputData;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        int cellWidth = this.getWidth() / inputData.getWidth();
        int cellHeight = this.getHeight() / inputData.getHeight();
        gd.setFont(new Font("courier new", Font.BOLD, cellHeight));
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
}
