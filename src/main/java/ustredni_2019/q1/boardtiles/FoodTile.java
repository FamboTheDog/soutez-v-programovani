package ustredni_2019.q1.boardtiles;

import java.awt.*;

public class FoodTile extends BoardTile {
    public FoodTile(int cost) {
        super(cost, 'J', Color.green);
    }

    @Override
    public void drawImpl(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
        gd.setColor(Color.BLACK);
        drawString(gd, x, y, cellWidth, cellHeight, Integer.toString(getCOST()));
    }
}
