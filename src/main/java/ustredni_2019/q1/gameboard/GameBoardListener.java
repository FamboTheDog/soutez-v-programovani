package ustredni_2019.q1.gameboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoardListener extends KeyAdapter {

    private final GameBoard gameBoard;

    public GameBoardListener(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> gameBoard.moveUp();
            case KeyEvent.VK_DOWN -> gameBoard.moveDown();
            case KeyEvent.VK_LEFT -> gameBoard.moveLeft();
            case KeyEvent.VK_RIGHT -> gameBoard.moveRight();
        }
    }
}
