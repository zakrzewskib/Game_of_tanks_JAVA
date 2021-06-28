package game_objects;

import game.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    public int width, height;
    public int xPosition, yPosition;
    public static Handler handler;

    public GameObject(int xPosition, int yPosition, int width, int height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public abstract void render(Graphics graphics);

    public Rectangle getBounds() {
        return new Rectangle(xPosition, yPosition, width, height);
    }

    public static void setHandler(Handler handler) {
        GameObject.handler = handler;
    }
}