package windows;

import game.Game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JFrame {

    private String userPath;
    private final int widthMenu = 420;
    private final int heightMenu = 400;

    public Menu() {
        super("Menu");
        JPanel menuPanel = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        menuPanel.setLayout(null);

        menuPanel.setSize(widthMenu, heightMenu);

        setContentPane(menuPanel);
        setSize(menuPanel.getSize());
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        JButton startButton = new JButton("START");
        JButton quitButton = new JButton("QUIT");
        JButton settingsButton = new JButton("CLICK TO WRITE FILE PATH");

        menuPanel.add(startButton);
        startButton.setBounds(60, 60, 300, 40);

        menuPanel.add(settingsButton);
        settingsButton.setBounds(60, 160, 300, 40);

        menuPanel.add(quitButton);
        quitButton.setBounds(60, 260, 300, 40);

        JTextField userText = new JTextField();
        userText.setBounds(60, 160, 300, 40);
        menuPanel.add(userText);
        userText.setVisible(false);

        quitButton.addActionListener(e -> System.exit(0));

        startButton.addActionListener(e -> {
            Game game;
            userPath = userText.getText();

            game = new Game(userPath);
            Thread gameThread = new Thread(game);
            gameThread.start();
            setVisible(false);
        });

        settingsButton.addActionListener(e -> {
            settingsButton.setVisible(false);
            userText.setVisible(true);
        });
    }
}