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

    public abstract void draw(Graphics2D gd, int x, int y, int width, int height);
}
