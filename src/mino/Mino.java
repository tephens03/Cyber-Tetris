package mino;

import java.awt.Color;
import java.awt.Graphics2D;

abstract public class Mino {
    public Block[] blocks;

    public Mino(Color c) {
        blocks = new Block[4];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(c);
        }
    }

    public void setXY(int x, int y) {
    }

    public void updateXY(int x, int y) {
    }

    public void update(int x, int y) {
    }

    public void draw(Graphics2D g2) {
        g2.setColor(blocks[0].color);
        for (Block block : blocks) {
            g2.fillRect(block.x, block.y, Block.SIZE - (2 * Block.MARGIN), Block.SIZE - (2 * Block.MARGIN));
        }
    }


}
