package game_objects;

import java.awt.Graphics;

public class MovingGameObject extends GameObject {

    double velocity;
    public PLayerID playerID;

    public enum PLayerID {PLAYER_1, PLAYER_2}

    public MovingGameObject(int xPosition, int yPosition, int width, int height, double velocity, PLayerID pLayerID) {
        super(xPosition, yPosition, width, height);
        this.velocity = velocity;
        this.playerID = pLayerID;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {

    }
}