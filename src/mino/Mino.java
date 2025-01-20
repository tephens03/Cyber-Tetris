package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import main.PlayManager;

abstract public class Mino {
    public Block[] blocks;
    public int autoDropCounter;

    public Mino(Color c) {
        autoDropCounter = 0;
        blocks = new Block[4];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(c);
        }
    }

    public void setXY(int x, int y) {
    }

    public void updateXY(int x, int y) {
    }

    public void update() {
        autoDropCounter++;
        if (autoDropCounter == PlayManager.DROP_INTERVAL) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].y += Block.SIZE;
            }
            autoDropCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        for (Block block : blocks) {
            g2.setColor(Color.WHITE);
            g2.fillRect(block.x, block.y, Block.SIZE, Block.SIZE);
            g2.setColor(blocks[0].color);
            g2.fillRect(block.x + Block.MARGIN, block.y + Block.MARGIN, Block.SIZE - (2 * Block.MARGIN),
                    Block.SIZE - (2 * Block.MARGIN));
        }
    }

}
