package game_objects;

import game.Game;
import game_objects.cell.Cell;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Bullet extends MovingGameObject {

    private final double angle;

    private static final double percentOfSizeBoard = 0.01;
    private final static int size = (int) (Math.sqrt(Board.width * Board.height) * percentOfSizeBoard);

    private static final int velocityBullet = 10;
    private int score;
    private final Tank tank;
    private final static LinkedList<GameObject> listOfCell = handler.getListOfCell();

    private static boolean canIncreaseVelocity = false;

    public Bullet(int xPosition, int yPosition, double angle, Tank tank) {
        super(xPosition, yPosition, size, size, velocityBullet, tank.playerID);
        this.angle = angle;
        handler.addBullet(this);
        this.tank = tank;
    }

    private boolean collided() {
        if (this.playerID == PLayerID.PLAYER_1) {
            if ((this.xPosition + size > Board.x + Board.width)) {
                return true;
            }
        } else if (this.playerID == PLayerID.PLAYER_2) {
            if ((this.xPosition < Board.x)) {
                return true;
            }
        }
        if ((this.yPosition < Board.y) || (this.yPosition + size > Board.y + Board.height)) {
            return true;
        }
        for (GameObject tempObject : listOfCell) {
            if (this.getBounds().intersects(tempObject.getBounds())) {
                Cell cell = (Cell) tempObject;
                score = cell.decreaseValue();
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        increaseVelocity();

        if (playerID == PLayerID.PLAYER_1) {
            yPosition += Math.sin(angle) * this.velocity;
            xPosition += Math.cos(angle) * this.velocity;
        } else {
            yPosition += Math.sin(Math.PI - angle) * this.velocity;
            xPosition += Math.cos(Math.PI - angle) * this.velocity;
        }

        if (collided()) {
            handler.removeBullet(this);
            tank.increaseNumOfBullets();
            tank.increaseScore(score);
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillOval(this.xPosition, this.yPosition, size, size);
    }

    public static void setCanIncreaseVelocity(Boolean flag) {
        canIncreaseVelocity = flag;
    }

    public void increaseVelocity() {
        if (canIncreaseVelocity)
            velocity = (velocity * (100 - Game.K)) / 100;
    }
}