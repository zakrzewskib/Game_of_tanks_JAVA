package game;

import game_objects.Bullet;
import game_objects.cell.Cell;
import game_objects.GameObject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Handler {
    private final LinkedList<GameObject> listOfCell = new LinkedList<>();
    private final LinkedList<GameObject> listOfBullets = new LinkedList<>();
    private final ArrayList<GameObject> listOfTanksBoardDisplays = new ArrayList<>();
    private final TimerAvailableUpdate timerAvailableUpdate;

    public Handler() {
        GameObject.setHandler(this);
        timerAvailableUpdate = new TimerAvailableUpdate();
    }

    public void addBullet(Bullet bullet) {
        this.listOfBullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        this.listOfBullets.remove(bullet);
    }

    public LinkedList<GameObject> getListOfCell() {
        return listOfCell;
    }

    public void addCell(Cell cell) {
        this.listOfCell.addFirst(cell);
    }

    public void removeCell(Cell cell) {
        this.listOfCell.remove(cell);
    }

    public void addListOfTanksBoardDisplays(GameObject gameObject) {
        this.listOfTanksBoardDisplays.add(gameObject);
    }

    public void startTimersUpdate() {
        timerAvailableUpdate.startTimers();
    }

    public void updateAll() {
        timerAvailableUpdate.setAvailableUpdates();
        updateCells();
        updateAllBullets();
        updateListOfTanksBoardDisplays();
    }

    private void updateCells() {
        iterateThroughTheList(listOfCell);
    }

    private void updateAllBullets() {
        iterateThroughTheList(listOfBullets);
    }

    private void iterateThroughTheList(LinkedList<GameObject> list) {
        int listSize = list.size();
        int newListSize;
        for (int i = 0; i < listSize; i++) {
            GameObject tmp = list.get(i);
            tmp.update();
            newListSize = list.size();
            try {
                if (newListSize > listSize) {
                    i += (newListSize - listSize);
                } else if (list.get(i) != tmp) {
                    i -= (newListSize - listSize);
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
            listSize = newListSize;
        }
    }

    private void updateListOfTanksBoardDisplays() {
        for (GameObject gameObject : listOfTanksBoardDisplays) {
            gameObject.update();
        }
    }

    public void renderAll(Graphics g) {
        renderIterateThroughTheList(listOfTanksBoardDisplays, g);
        renderIterateThroughTheList(listOfCell, g);
        renderIterateThroughTheList(listOfBullets, g);
    }

    private void renderIterateThroughTheList(List<GameObject> list, Graphics graphics) {
        for (GameObject gameObject : list) {
            gameObject.render(graphics);
        }
    }
}