package ustredni_2019.q1.boardtiles;

import java.awt.*;

public class WallTile extends BoardTile {
    public WallTile(int cost) {
        super(cost, 'Z', Color.BLACK);
    }

    @Override
    public void draw(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
        gd.setColor(getCOLOR());
        gd.fillRect(x, y, cellWidth, cellHeight);
        gd.setColor(Color.WHITE);
        gd.drawString(Integer.toString(getCOST()), x, y + cellHeight);
    }
}
