package game_objects;

import game_objects.cell.Cell;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class DisplayInformation extends GameObject {

    public DisplayInformation() {
        super(Board.x + (Board.width - Cell.startCellSize * 8) / 2,
                Board.y - Cell.startCellSize, Cell.startCellSize * 8, Cell.startCellSize);
        handler.addListOfTanksBoardDisplays(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        drawPointsAndColors(graphics);
    }

    private void drawPointsAndColors(Graphics g) {
        int width = Cell.startCellSize * 8;
        int height = Cell.startCellSize;
        String imgPath = "/images/pointsAndColors.png";
        try {
            g.drawImage(ImageIO.read(DisplayInformation.class.getResource(imgPath)), this.xPosition
                    , this.yPosition, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}