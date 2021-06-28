package game_objects;

import game.Game;
import game_objects.cell.Cell;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public final class Board extends GameObject {

    public static int width;
    public static int height;
    public static int x;
    public static int y;

    private static boolean canCreateCell = false;
    private final int numberOfStartingCells = 4;

    private final Random random = new Random();

    private boolean startOfTheGame = true;

    public Board() {
        super(x, y, width, height);
        this.setStartPosition();
        handler.addListOfTanksBoardDisplays(this);
        createStartingCells(numberOfStartingCells, startOfTheGame);
    }

    private void setStartPosition() {
        width = (Game.WIDTH * 7) / 10;
        height = (Game.HEIGHT * 7) / 10;
        y = (Game.HEIGHT - height) / 2 - (Game.HEIGHT - height) / 7;
        x = Game.WIDTH / 2 - width / 2;
    }

    @Override
    public void update() {
        if (canCreateCell) {
            createRandomCell();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }

    private int randomValue() {
        return random.nextInt(8) + 1;
    }

    private int randomCellSize() {
        return x + random.nextInt(width - Cell.currentCellSize) + 1;
    }

    private void createStartingCells(int n, boolean flag) {
        if (flag) {
            int i = 0;
            while (i < n) {
                createRandomCell();
                i++;
            }
        }
        this.startOfTheGame = false;
    }

    private void createRandomCell() {
        int attemptsCreateCell = 10;

        while (attemptsCreateCell > 0) {
            int cellX = randomCellSize();
            int cellY = randomCellSize();

            Cell cell = new Cell(cellX, cellY, randomValue());

            if (cell.addCell()) {
                break;
            }
            attemptsCreateCell--;
        }
    }

    public static void setCanCreateCell(boolean flag) {
        Board.canCreateCell = flag;
    }
}