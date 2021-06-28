package game;

import game_objects.MovingGameObject;
import game_objects.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Tank tank;
    private int key;
    private boolean nextKey;
    private final int moveTankUp;
    private final int moveTankDown;
    private final int moveBarrelDown;
    private final int moveBarrelUp;
    private final int keyShot;

    public KeyInput(Tank tank) {
        this.tank = tank;
        nextKey = true;

        int iD = tank.playerID == MovingGameObject.PLayerID.PLAYER_1 ? 1 : 2;

        moveBarrelUp = iD == 1 ? KeyEvent.VK_A : KeyEvent.VK_LEFT;
        moveBarrelDown = iD == 1 ? KeyEvent.VK_D : KeyEvent.VK_RIGHT;
        moveTankUp = iD == 1 ? KeyEvent.VK_W : KeyEvent.VK_UP;
        moveTankDown = iD == 1 ? KeyEvent.VK_S : KeyEvent.VK_DOWN;
        keyShot = iD == 1 ? KeyEvent.VK_SPACE : KeyEvent.VK_ENTER;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (nextKey) {
            key = e.getKeyCode();
        }
        if (key == moveTankUp) {
            tank.canMoveUp = true;
            nextKey = false;
        } else if (key == moveTankDown) {
            tank.canMoveDown = true;
            nextKey = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyReleased;
        keyReleased = e.getKeyCode();
        if (keyReleased == moveTankUp) {
            tank.canMoveUp = false;
            nextKey = true;
        } else if (keyReleased == moveTankDown) {
            tank.canMoveDown = false;
            nextKey = true;
        }
        if (keyReleased == moveBarrelUp) {
            tank.canBarrelUp = true;
        } else if (keyReleased == moveBarrelDown) {
            tank.canBarrelDown = true;
        }
        if (keyReleased == keyShot) {
            tank.canShoot = true;
        }
    }
}