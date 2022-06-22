package ustredni_2019.q1.boardtiles;

import java.awt.*;

public class WallTile extends BoardTile {
    public WallTile(int cost) {
        super(cost, 'Z', Color.BLACK);
    }

    @Override
    public void drawImpl(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
        gd.setColor(Color.WHITE);
        drawString(gd, x, y, cellWidth, cellHeight, Integer.toString(getCOST()));
    }
}
