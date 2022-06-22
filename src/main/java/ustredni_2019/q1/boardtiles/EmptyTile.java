package ustredni_2019.q1.boardtiles;

import ustredni_2019.q1.GameBoard;
import ustredni_2019.q1.InputData;

import java.awt.*;

public class EmptyTile extends BoardTile {
    public EmptyTile() {
        super(1, 'V', Color.WHITE);
    }

    @Override
    public void draw(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
        gd.setColor(getCOLOR());
        gd.fillRect(x, y, cellWidth, cellHeight);
    }
}
