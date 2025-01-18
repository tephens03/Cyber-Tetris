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
        blocks[0].x = x;
        blocks[0].y = y;

        blocks[1].x = x;
        blocks[1].y = y - Block.SIZE;

        blocks[2].x = x;
        blocks[2].y = y + Block.SIZE;

        blocks[3].x = x + Block.SIZE;
        blocks[3].y = y + Block.SIZE;

    }

}
