package kraj_2022.q3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveListener extends MouseAdapter {

    private BoardPanel boardPanel;

    private boolean isPlayerOneMove;

    public MoveListener(BoardPanel boardPanel, boolean whichPlayerStarts) {
        this.boardPanel = boardPanel;
        this.isPlayerOneMove = whichPlayerStarts;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        char toWrite = isPlayerOneMove ? 'X' : 'O';
        isPlayerOneMove = !isPlayerOneMove;
        boardPanel.write(e.getX(), e.getY(), toWrite);
        boardPanel.repaint();
    }

    public void setPlayerOneMove(boolean playerOneMove) {
        isPlayerOneMove = playerOneMove;
    }
}
