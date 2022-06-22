package ustredni_2019.q1;

import com.google.gson.Gson;
import ustredni_2019.q1.boardtiles.*;
import ustredni_2019.q1.gson.templates.JsonData;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final JFrame frame = new JFrame("Pac-man");
    private static GameBoard gameBoard = null;

    public static void main(String[] args) throws FileNotFoundException {
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(640, 480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());


        addMenuBar();

        frame.revalidate();
        frame.repaint();
    }

    private static InputData getInputData(JsonData jsonData) {
        InputData inputData = new InputData();
        inputData.setWidth(jsonData.getSIRKA());
        inputData.setHeight(jsonData.getVYSKA());
        inputData.setStart(jsonData.getSTART());
        inputData.setEnd(jsonData.getCIL());
        inputData.setBoardPlan(constructBoard(jsonData.getPLAN(), jsonData.getSIRKA(), jsonData.getVYSKA()));
        System.out.println(Arrays.deepToString(inputData.getBoardPlan()));
        return inputData;
    }

    private static BoardTile[][] constructBoard(String[] plan, int boardWidth, int boardHeight) {
        BoardTile[][] board = new BoardTile[boardHeight][boardWidth];

        for (int i = 0; i < plan.length; i++) {
            for (int j = 0, inRowIndex = 0; j < plan[i].length(); j++) {
                BoardTile newTile = null;
                switch (plan[i].charAt(j)) {
                    case 'V' -> newTile = new EmptyTile();
                    case 'J' -> newTile = new FoodTile(charToInt(plan[i].charAt(j + 1)));
                    case 'Z' -> {
                        int height = 0;
                        if (j + 1 < plan[i].length()) {
                            height = charToInt(plan[i].charAt(j + 1));
                        }
                        newTile = new WallTile(height);
                    }
                    case 'B' -> {
                        int damage = 0;
                        if (j + 1 < plan[i].length()) {
                            damage = charToInt(plan[i].charAt(j + 1));
                        }
                        newTile = new BombTile(damage);
                    }
                }
                if (newTile != null) {
                    board[i][inRowIndex] = newTile;
                    inRowIndex++;
                }
            }
        }
        return board;
    }

    private static int charToInt(char c) {
        return Integer.parseInt(Character.toString(c));
    }

    private static void addMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem item = new JMenuItem("Load level");
        menu.add(item);
        bar.add(menu);

        frame.add(bar, BorderLayout.NORTH);

        item.addActionListener(v-> {
            FileDialog dialog = new FileDialog(frame, "Choose an input file");
            dialog.setFilenameFilter((dir, name) -> name.endsWith(".json"));
            dialog.setVisible(true);

            Gson gson = new Gson();
            try {
                JsonData data = gson.fromJson(new FileReader(dialog.getDirectory() + dialog.getFile()), JsonData.class);
                if (gameBoard != null) {
                    frame.remove(gameBoard);
                }
                gameBoard = new GameBoard(getInputData(data));
                frame.add(gameBoard, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
