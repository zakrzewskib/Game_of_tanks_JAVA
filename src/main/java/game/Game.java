package game;

import game_objects.Board;
import game_objects.DisplayInformation;
import game_objects.MovingGameObject;
import game_objects.Tank;
import game_objects.Timer;
import windows.Window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public final class Game implements Runnable {
    public static int WIDTH;
    public static int HEIGHT;
    public static int P, H, X, Y, Z, K, L;
    public static int TIME, WIN_SCORE;

    public boolean gameState = false;

    public static boolean gameOver = false;

    public Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private final Handler handler;

    public Tank tank1;
    public Tank tank2;

    private Window window;

    public static long now;
    private GameOverController gameOverController;

    public Game(String pathInput) {
        canvas = new Canvas();
        Utility utility = new Utility();
        try {
            utility.readFile(pathInput);
        } catch (IOException | NullPointerException ignored) {

        }
        window = new Window(WIDTH, HEIGHT, "Game of Tanks", canvas);
        handler = new Handler();
        start();
    }

    private void start() {
        gameState = true;
        new Board();
        tank1 = new Tank(0, HEIGHT / 2 - Tank.defaultHeight, MovingGameObject.PLayerID.PLAYER_1);
        tank2 = new Tank(WIDTH - Tank.defaultWidth - Tank.defaultWidth / 8, HEIGHT / 2 - Tank.defaultHeight,
                MovingGameObject.PLayerID.PLAYER_2);
        canvas.addKeyListener(new KeyInput(tank1));
        canvas.addKeyListener(new KeyInput(tank2));
        new Timer();
        new DisplayInformation();
        gameOverController = new GameOverController(this);
    }

    @Override
    public void run() {
        canvas.requestFocus();

        double nanoSecondFpsConversion = 1000_000_000.0 / 60;
        double deltaTime = 0;

        long lastTime = now;
        handler.startTimersUpdate();

        while (gameState) {
            now = System.nanoTime();
            deltaTime += (now - lastTime) / nanoSecondFpsConversion;
            while (deltaTime >= 1) {
                update();
                deltaTime = 0;
            }
            render();
            lastTime = now;

            if (gameOver) {
                gameOverController.gameOver();
            }
        }
    }
    
    private void update() {
        handler.updateAll();
    }

    private void render() {
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();

        paintGraphics(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    void paintGraphics(Graphics graphics) {
        graphics.setColor(Color.gray);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        handler.renderAll(graphics);
    }

}