package game_objects.cell;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class RenderCell {

    private final Cell cell;

    private static Font monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, Cell.currentCellSize);
    private static int centerHeightSize;
    private static int centerWidthSize;

    public RenderCell(Cell cell) {
        this.cell = cell;
    }

    void render(Graphics g, int maxValue, int value) {
        switch (maxValue) {
            case 1 -> g.setColor(Color.pink);
            case 2 -> g.setColor(Color.red);
            case 3 -> g.setColor(new Color(255, 153, 0));
            case 4 -> g.setColor(Color.yellow);
            case 5 -> g.setColor(Color.green);
            case 6 -> g.setColor(Color.cyan);
            case 7 -> g.setColor(Color.blue);
            case 8 -> g.setColor(new Color(102, 0, 153));
            default -> g.setColor(Color.black);
        }
        g.fillRect(cell.xPosition, cell.yPosition, cell.width, cell.height);

        changeSizeDisposeString(g, value);

        g.setColor(Color.black);
        g.drawString(Integer.toString(value), cell.xPosition + centerWidthSize, cell.yPosition + cell.height - centerHeightSize);
    }

    private void changeSizeDisposeString(Graphics g, int value) {
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, Cell.currentCellSize);
        g.setFont(monoFont);
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth(Integer.toString(value));

        centerHeightSize = Cell.currentCellSize / 7;
        centerWidthSize = (Cell.currentCellSize - stringWidth) / 2;
    }
}