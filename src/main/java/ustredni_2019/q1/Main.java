package ustredni_2019.q1;

import com.google.gson.Gson;
import ustredni_2019.q1.boardtiles.*;
import ustredni_2019.q1.gson.templates.JsonData;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    private static final JFrame frame = new JFrame("Pac-man");

    public static void main(String[] args) throws FileNotFoundException {
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(640, 480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());

        FileDialog dialog = new FileDialog(frame, "Choose an input file");
        dialog.setFilenameFilter((dir, name) -> name.endsWith(".json"));
        dialog.setVisible(true);

        Gson gson = new Gson();
        JsonData data = gson.fromJson(new FileReader(dialog.getDirectory() + dialog.getFile()), JsonData.class);

        addGameBoard(getInputData(data));

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

    private static void addGameBoard(InputData data) {
        frame.add(new GameBoard(data), BorderLayout.CENTER);
        System.out.println("added");
    }

}
