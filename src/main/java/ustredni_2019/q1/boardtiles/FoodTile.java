package ustredni_2019.q1.boardtiles;

import java.awt.*;

public class FoodTile extends BoardTile {
    public FoodTile(int cost) {
        super(cost, 'J', Color.green);
    }

    @Override
    public void draw(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
        gd.setColor(getCOLOR());
        gd.fillRect(x, y, cellWidth, cellHeight);
        gd.setColor(Color.BLACK);
        gd.drawString(Integer.toString(getCOST()), x, y + cellHeight);
    }
}
