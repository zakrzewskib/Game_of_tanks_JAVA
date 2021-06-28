package game_objects;

import game.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class DisplayScore extends GameObject {

    private final Tank tank;
    private String scoreString;
    private static Font monoFont;

    public DisplayScore(Tank tank) {
        super(0, 0, Board.x, Board.y);
        handler.addListOfTanksBoardDisplays(this);

        if (tank.playerID == MovingGameObject.PLayerID.PLAYER_2) {
            xPosition = Board.x + Board.width;
            yPosition = 0;
            this.width = Game.WIDTH - (Board.x + Board.width);
            this.height = Board.y;
        }
        this.tank = tank;
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, (int) (Math.sqrt(width * height) / 4));

    }


    @Override
    public void update() {
        int score = tank.getScore();
        scoreString = Integer.toString(score);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(xPosition, yPosition, width, height);

        graphics.setColor(Color.black);
        graphics.setFont(monoFont);

        FontMetrics fm = graphics.getFontMetrics();
        int widthStringScore = fm.stringWidth("Score");
        int heightString = fm.getAscent();
        int widthInterScore = fm.stringWidth(scoreString);

        graphics.drawString("Score", xPosition + width / 2 - widthStringScore / 2, yPosition + heightString);
        graphics.drawString(scoreString, xPosition + width / 2 - widthInterScore / 2, yPosition + height);
    }
}
