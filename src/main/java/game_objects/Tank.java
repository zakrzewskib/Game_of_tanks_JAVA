package game_objects;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Tank extends MovingGameObject {
    private static int numOfBullets = Game.P;

    private int angle = 0;
    private final static double barrelAngleDifference = 15;
    private int bulletStartPosX;
    private int bulletStartPosY;

    private int score = 0;
    public static int defaultWidth = Board.width / 6;
    public static int defaultHeight = Board.height / 6;
    public static int defaultVelocity = 5;

    public boolean canShoot;
    public boolean canMoveUp;
    public boolean canMoveDown;
    public boolean canBarrelUp;
    public boolean canBarrelDown;

    public Tank(int xPosition, int yPosition, PLayerID playerID) {
        super(xPosition, yPosition, defaultWidth, defaultHeight, defaultVelocity, playerID);
        handler.addListOfTanksBoardDisplays(this);
        new DisplayScore(this);
    }

    @Override
    public void update() {
        shootBullet();
        if (!moveUp()) {
            moveDown();
        }
        if (!moveBarrelUp()) {
            moveBarrelDown();
        }
    }

    private void shootBullet() {
        decideStartBulletPos();
        if (numOfBullets > 0 && canShoot) {
            new Bullet(bulletStartPosX, bulletStartPosY, Math.PI * angle / 180, this);
            numOfBullets--;
            canShoot = false;
        }
    }

    private void decideStartBulletPos() {
        if (playerID == PLayerID.PLAYER_1) {
            bulletStartPosX = this.xPosition + this.width;
        } else {
            bulletStartPosX = this.xPosition;
        }
        int barrelPosY = (int) (this.yPosition + this.height / 2.3);
        int barrelPosYDiff = this.height / 5;
        bulletStartPosY = barrelPosY;
        bulletStartPosY += (angle / barrelAngleDifference) * barrelPosYDiff;
    }

    private boolean moveUp() {
        if ((this.yPosition > Board.y) && canMoveUp) {
            yPosition -= velocity;
            return true;
        }
        return false;
    }

    private boolean moveDown() {
        if ((this.yPosition < Board.y + Board.height - this.height) && canMoveDown) {
            yPosition += velocity;
            return true;
        }
        return false;
    }

    private boolean moveBarrelUp() {
        if ((this.angle > -45) && canBarrelUp) {
            this.angle -= barrelAngleDifference;
            canBarrelUp = false;
            return true;
        }
        canBarrelUp = false;
        return false;
    }

    private boolean moveBarrelDown() {
        if ((this.angle < 45) && canBarrelDown) {
            this.angle += barrelAngleDifference;
            canBarrelDown = false;
            return true;
        }
        canBarrelDown = false;
        return false;
    }

    @Override
    public void render(Graphics g) {
        String imgPath = decideTankImage();
        try {
            g.drawImage(ImageIO.read(Tank.class.getResource(imgPath)), this.xPosition, this.yPosition, this.width, this.height, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String decideTankImage() {
        String imgPath;
        String path = "/images/";
        if (playerID == PLayerID.PLAYER_1) {
            imgPath = path + "tankLeft/tankLeft" + angle + ".png";
        } else {
            imgPath = path + "tankRight/tankRight" + angle + ".png";
        }
        return imgPath;
    }

    public int getScore() {
        return score;
    }

    public void increaseNumOfBullets() {
        numOfBullets++;
    }

    public void increaseScore(int points) {
        score += points;
        if (score >= Game.WIN_SCORE) {
            Game.gameOver = true;
        }
    }
}