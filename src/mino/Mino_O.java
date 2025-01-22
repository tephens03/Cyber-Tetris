package mino;

import java.awt.Color;

public class Mino_O extends Mino {
    public Mino_O() {
        super(Color.YELLOW);
    }

    /*
     * ---b1-b2
     * ---b0-b3
     * --------
     */
    public void setXY(int x, int y) {
        blocks[0].x = x;
        blocks[0].y = y;

        blocks[1].x = blocks[0].x;
        blocks[1].y = blocks[0].y - Block.SIZE;

        blocks[2].x = blocks[0].x + Block.SIZE;
        blocks[2].y = blocks[0].y - Block.SIZE;

        blocks[3].x = blocks[0].x + Block.SIZE;
        blocks[3].y = blocks[0].y;
    }

    /*
     * ---b1-b2
     * ---b0-b3
     * --------
     */
    public void updateDirection2() {
    }

    /*
     * ---b1-b2
     * ---b0-b3
     * --------
     */
    public void updateDirection3() {
    }

    /*
     * ---b1-b2
     * ---b0-b3
     * --------
     */
    public void updateDirection4() {
    }

    /*
     * ---b1-b2
     * ---b0-b3
     * --------
     */
    public void updateDirection1() {
    }
}
