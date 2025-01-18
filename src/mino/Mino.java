package mino;

import java.awt.Color;
import java.awt.Graphics2D;

public class Mino {
    public Block[] blocks = new Block[4];

    public Mino(Color c) {

        blocks[0] = new Block(c);
        blocks[1] = new Block(c);
        blocks[2] = new Block(c);
        blocks[3] = new Block(c);

    }

    // public void setXY(int x, int y) {
    // }

    public void updateXY(int x, int y) {
        
    }

    public void update(int x, int y) {
    }

    public void draw(Graphics2D g2) {
        g2.setColor(blocks[0].color);

        g2.fillRect(blocks[0].x, blocks[0].y, Block.SIZE, Block.SIZE);
        System.out.println(blocks[0].x + " " + blocks[0].y);

        g2.fillRect(blocks[1].x, blocks[1].y, Block.SIZE, Block.SIZE);
        System.out.println(blocks[1].x + " " + blocks[1].y);

        g2.fillRect(blocks[2].x, blocks[2].y, Block.SIZE, Block.SIZE);
        System.out.println(blocks[2].x + " " + blocks[2].y);

        g2.fillRect(blocks[3].x, blocks[3].y, Block.SIZE, Block.SIZE);
        System.out.println(blocks[3].x + " " + blocks[3].y);

    }
}
