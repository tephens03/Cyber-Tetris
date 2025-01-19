package mino;

import java.awt.Color;

public class Mino_L extends Mino {

    public Mino_L() {
        super(Color.ORANGE);
    }

    public void setXY(int x, int y) {
        // b1
        // b0
        // b2 b3
        blocks[0].x = x + Block.MARGIN;
        blocks[0].y = y + Block.MARGIN;

        blocks[1].x = blocks[0].x;
        blocks[1].y = blocks[0].y - Block.SIZE;

        blocks[2].x = blocks[0].x;
        blocks[2].y = blocks[0].y + Block.SIZE;

        blocks[3].x = blocks[0].x + Block.SIZE;
        blocks[3].y = blocks[0].y + Block.SIZE;
    }

}
