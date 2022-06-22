package ustredni_2019.q1.boardtiles;

import java.awt.*;

public class EmptyTile extends BoardTile {
    public EmptyTile() {
        super(1, 'V', Color.WHITE);
    }

    @Override
    public void drawImpl(Graphics2D gd, int x, int y, int cellWidth, int cellHeight) {
    }
}
