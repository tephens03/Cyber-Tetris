package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.PlayManager;

/**
 * The Block class represents a single block in the Tetris playfield (200 block
 * in total).
 * Each block has a position (x, y), a size, and a color. It is also a
 * subclass of Rectangle, which means each block has rectangular dimensions
 * and can be used in grid-based positioning.
 */
public class Block extends Rectangle {

    /**
     * The size of each block. This is calculated dynamically based on the
     * playfield's width
     * and ensures that blocks fit within the grid. It's calculated as 1/10th of the
     * playfield width.
     */
    public static int SIZE = PlayManager.PLAYFIELD_WIDTH / 10;
    public static int MARGIN = 2;// The margin between blocks
    public int x; // The x-coordinate of the block on the playfield.
    public int y; // The y-coordinate of the block on the playfield.
    public Color color;// The color of the block.

    /**
     * Constructs a new Block with the specified color.
     *
     * @param color The color of the block.
     */
    public Block(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillOval(x, y, Block.SIZE, Block.SIZE); // Draw the outline

        g2.setColor(color);
        g2.fillOval(x + Block.MARGIN, y + Block.MARGIN, Block.SIZE - (2 * Block.MARGIN),
                Block.SIZE - (2 * Block.MARGIN)); // Fill the block with its color
        // g2.fillRect(block.x + Block.MARGIN, block.y + Block.MARGIN, Block.SIZE - (2 *
        // Block.MARGIN),
        // Block.SIZE - (2 * Block.MARGIN)); // Fill the block with its color
    }
}
