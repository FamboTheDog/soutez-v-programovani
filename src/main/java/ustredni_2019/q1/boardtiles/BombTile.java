package ustredni_2019.q1.boardtiles;

import java.awt.*;

public class BombTile extends BoardTile {
    public BombTile(int cost) {
        super(cost, 'B', Color.RED);
    }

    @Override
    public void drawImpl(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
        gd.setColor(Color.BLACK);
        drawString(gd, x, y, cellWidth, cellHeight, Integer.toString(getCOST()));
    }
}
