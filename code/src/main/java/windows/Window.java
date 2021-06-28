package windows;

import javax.swing.JFrame;
import java.awt.Canvas;

public class Window extends JFrame {

    public Window(int width, int height, String title, Canvas canvas) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        add(canvas);
        setVisible(true);
        canvas.createBufferStrategy(3);
    }
}