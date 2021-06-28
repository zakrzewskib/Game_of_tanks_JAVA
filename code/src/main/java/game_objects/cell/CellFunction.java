package game_objects.cell;

import game_objects.Board;
import game_objects.GameObject;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class CellFunction {

    private final Cell cell;

    private static boolean thereIsOneArmageddon;
    private static final Random random = new Random();

    private final double percentToByArmageddon = 0.1;

    public CellFunction(Cell cell) {
        this.cell = cell;
    }

    int bonusPoints() {
        int chanceToBeBonusPoints = 1 + random.nextInt(100);
        if (chanceToBeBonusPoints % 100 == 0) {
            return 100;
        } else if (chanceToBeBonusPoints % 50 == 0) {
            return 50;
        } else if (chanceToBeBonusPoints % 30 == 0) {
            return 30;
        } else if (chanceToBeBonusPoints % 20 == 0) {
            return 20;
        } else if (chanceToBeBonusPoints % 10 == 0) {
            return 10;
        }
        return 0;
    }

    boolean isArmageddon() {
        if (!thereIsOneArmageddon) {
            int chanceToBeArmageddon = random.nextInt((int) (1 / percentToByArmageddon));
            if (chanceToBeArmageddon == 0) {
                thereIsOneArmageddon = true;
                return true;
            }
        }
        return false;
    }

    boolean notCollided() {
        if ((cell.xPosition < Board.x) || (cell.xPosition + cell.width > Board.x + Board.width)) {
            return false;
        }
        if ((cell.yPosition < Board.y) || (cell.yPosition + cell.height > Board.y + Board.height)) {
            return false;
        }

        for (GameObject tempObject : Cell.listOfCell) {
            if (cell.getBounds().intersects(tempObject.getBounds())) {
                return false;
            }
        }
        return true;
    }

    static ArrayList<Point> createListOfPointToBornChild() {
        ArrayList<Point> listOfPointToBornChild = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (!(x == 0 && y == 0)) {
                    Point point = new Point(x, y);
                    listOfPointToBornChild.add(point);
                }
            }
        }
        return listOfPointToBornChild;
    }

}