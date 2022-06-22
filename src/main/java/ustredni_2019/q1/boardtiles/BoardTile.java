package ustredni_2019.q1.boardtiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
public abstract class BoardTile {
    private final int COST;
    private final char SIGN;
    private final Color COLOR;

    public void draw(Graphics2D gd, int x, int y, int width, int height) {
        gd.setColor(getCOLOR());
        gd.fillRect(x, y, width, height);
        gd.setColor(Color.lightGray);
        gd.drawRect(x, y, width, height);
        gd.setColor(getCOLOR());

        drawImpl(gd, x, y, width, height);
    }
    protected abstract void drawImpl(Graphics2D gd, int x, int y, int width, int height);

    protected void drawString(Graphics2D gd, int x, int y, int cellWidth, int cellHeight, String text) {
        FontMetrics metrics = gd.getFontMetrics(gd.getFont());
        int stringX = x + (cellWidth - metrics.stringWidth(Integer.toString(getCOST()))) / 2;
        int stringY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent();
        gd.drawString(text, stringX, stringY);
    }

}
