package ustredni_2019.q1.boardtiles;

import java.awt.*;

public class BombTile extends BoardTile {
    public BombTile(int cost) {
        super(cost, 'B', Color.RED);
    }

    @Override
    public void draw(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
        gd.setColor(getCOLOR());
        gd.fillRect(x, y, cellWidth, cellHeight);
        gd.setColor(Color.BLACK);
        gd.drawString(Integer.toString(getCOST()), x, y + cellHeight);
    }
}
