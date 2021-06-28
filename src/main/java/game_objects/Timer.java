package game_objects;

import game.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public final class Timer extends GameObject {

    private static Font monoFont;
    private int timeToEnd;
    private long timeLastUpdate;

    public Timer() {
        super(Board.x + Board.width / 3, Board.y + Board.height,
                Board.width / 3, Game.HEIGHT - (Board.y + Board.height));
        handler.addListOfTanksBoardDisplays(this);
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, (int) (Math.sqrt(width * height) / 8));
        timeLastUpdate = Game.now;
        timeToEnd = Game.TIME;
    }

    @Override
    public void update() {
        long oneSecond = 100_000_0000;
        if ((Game.now - timeLastUpdate) > oneSecond) {
            timeToEnd -= 1;
            timeLastUpdate = Game.now;
        }
        if (timeToEnd == 0) {
            Game.gameOver = true;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(xPosition, yPosition, width, height);

        graphics.setFont(monoFont);
        FontMetrics fm = graphics.getFontMetrics();
        int stringWidth = fm.stringWidth("Time: 000");
        int heightString = fm.getAscent();

        graphics.setColor(Color.black);
        graphics.drawString("Time: " + timeToEnd, xPosition + width / 2 - stringWidth / 2,
                yPosition + height / 2 - heightString / 2);
    }
}