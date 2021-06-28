package game_objects.cell;

import game.Game;
import game_objects.Board;
import game_objects.GameObject;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Cell extends GameObject {

    private final boolean isArmageddon;
    private final int bonusPoints;

    private static boolean canInsertValue = false;
    private static boolean canDecreaseSize = false;
    private static boolean canBornChild = false;

    final static ArrayList<Point> listOfPointToBornChild = CellFunction.createListOfPointToBornChild();

    private int maxValue;
    private int value;
    private static final double percentOfSizeBoard = 0.1;

    private boolean changeSizeBornChildren;

    public static final int startCellSize = (int) (Math.sqrt(Board.width * Board.height) * percentOfSizeBoard);
    public static int currentCellSize = startCellSize;

    static final LinkedList<GameObject> listOfCell = handler.getListOfCell();
    private final CellFunction cellFunction;
    private final RenderCell renderCell;

    public Cell(int xPosition, int yPosition, int maxValue) {
        super(xPosition, yPosition, currentCellSize, currentCellSize);

        this.maxValue = maxValue;
        this.value = maxValue;

        cellFunction = new CellFunction(this);
        renderCell = new RenderCell(this);

        isArmageddon = cellFunction.isArmageddon();
        bonusPoints = cellFunction.bonusPoints();
    }


    @Override
    public void update() {
        increaseValue();
        decreaseSize();
        bornChild();
    }

    @Override
    public void render(Graphics g) {
        renderCell.render(g, maxValue, value);
    }

    private void increaseValue() {
        if (canInsertValue) {
            if (this.value < 8) {
                this.value++;
                if (value > maxValue) {
                    this.maxValue = value;
                }
            }
        }
    }

    private void decreaseSize() {
        if ((canDecreaseSize) && (width > startCellSize / 2) && (height > startCellSize / 2)) {
            width = ((100 - Game.L) * width) / 100;
            height = ((100 - Game.L) * height) / 100;

            if (changeSizeBornChildren) {
                currentCellSize = width;
                changeSizeBornChildren = false;
            }
        } else {
            changeSizeBornChildren = true;
        }
    }

    private void bornChild() {
        if (canBornChild) {
            int amountChild = this.maxValue - 1;
            Collections.shuffle(listOfPointToBornChild);
            for (Point p : listOfPointToBornChild) {
                int xPosition = this.xPosition + p.x * this.width;
                int yPosition = this.yPosition + p.y * this.height;
                Cell cell = new Cell(xPosition, yPosition, 1);
                if (cell.addCell()) {
                    amountChild--;
                    if (amountChild <= 0) {
                        break;
                    }
                }
            }
        }
    }

    public int decreaseValue() {
        value--;
        if (value <= 0) {
            handler.removeCell(this);
            if (isArmageddon) {
                return Game.WIN_SCORE;
            }
            return maxValue + bonusPoints;
        }
        return 0;
    }

    public boolean addCell() {
        if (cellFunction.notCollided()) {
            handler.addCell(this);
            return true;
        }
        return false;
    }

    public static void setCanInsertValue(Boolean flag) {
        canInsertValue = flag;
    }

    public static void setCanDecreaseSize(Boolean flag) {
        canDecreaseSize = flag;
    }

    public static void setCanBornChild(Boolean flag) {
        canBornChild = flag;
    }
}