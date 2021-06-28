package game;

import game_objects.Board;
import game_objects.Bullet;
import game_objects.cell.Cell;

public class TimerAvailableUpdate {

    private long lastTimeSinceSpawn;
    private long lastTimeSinceIncreasedValue;
    private long lastTimeSinceDecreasedSize;
    private long lastTimeSinceBornChild;

    private final long oneSecond = 100_000_0000;


    public TimerAvailableUpdate() {
    }
    
    public void startTimers() {
        long now = System.nanoTime();
        lastTimeSinceSpawn = now;
        lastTimeSinceIncreasedValue = now;
        lastTimeSinceDecreasedSize = now;
        lastTimeSinceBornChild = now;
    }

    public void setAvailableUpdates() {
        setAvailableSpawn();
        setAvailableIncreaseValue();
        setAvailableDecreaseSizeAndIncreaseBulletVelocity();
        setAvailableBornChild();
    }

    private void setAvailableSpawn() {
        if (Game.now - lastTimeSinceSpawn >= Game.H * oneSecond) {
            lastTimeSinceSpawn = Game.now;
            Board.setCanCreateCell(true);
        } else {
            Board.setCanCreateCell(false);
        }
    }

    private void setAvailableIncreaseValue() {
        if (Game.now - lastTimeSinceIncreasedValue >= Game.Y * oneSecond) {
            lastTimeSinceIncreasedValue = Game.now;
            Cell.setCanInsertValue(true);
        } else {
            Cell.setCanInsertValue(false);
        }
    }

    private void setAvailableDecreaseSizeAndIncreaseBulletVelocity() {
        if (Game.now - lastTimeSinceDecreasedSize >= Game.Z * oneSecond) {
            lastTimeSinceDecreasedSize = Game.now;
            Cell.setCanDecreaseSize(true);
            Bullet.setCanIncreaseVelocity(true);
        } else {
            Cell.setCanDecreaseSize(false);
            Bullet.setCanIncreaseVelocity(false);
        }
    }

    private void setAvailableBornChild() {
        if (Game.now - lastTimeSinceBornChild >= Game.X * oneSecond) {
            lastTimeSinceBornChild = Game.now;
            Cell.setCanBornChild(true);
        } else {
            Cell.setCanBornChild(false);
        }
    }

}