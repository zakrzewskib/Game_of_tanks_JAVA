package game;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameOverController {
    private static Game game;

    public GameOverController(Game game) {
        GameOverController.game = game;
    }

    public void gameOver() {
        game.gameState = false;
        String message;
        int score;

        if (game.tank1.getScore() > game.tank2.getScore()) {
            message = "The winner is PLAYER1!";
            score = game.tank1.getScore();
        } else if (game.tank2.getScore() > game.tank1.getScore()) {
            message = "The winner is PLAYER2!";
            score = game.tank2.getScore();
        } else {
            message = "It's a tie!";
            score = game.tank1.getScore();
        }

        renderLastTimeAndSaveImage();
        JOptionPane.showMessageDialog(Main.menu, message + " score = " + score,
                "Game Over", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }

    private void renderLastTimeAndSaveImage() {
        BufferedImage image = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();
        game.paintGraphics(graphics);
        graphics.dispose();

        try {
            FileOutputStream out = new FileOutputStream("GameOverScreen.png");
            ImageIO.write(image, "png", out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}