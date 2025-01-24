package mino;

import java.awt.Color;

public class Mino_L extends Mino {

    public Mino_L() {
        super(Color.ORANGE);
    }

    /*
     * ------b3
     * b1-b0-b2
     * --------
     */
    public void setXY(int x, int y) {
        blocks[0].x = x;
        blocks[0].y = y;

        blocks[1].x = blocks[0].x - Block.SIZE;
        blocks[1].y = blocks[0].y;

        blocks[2].x = blocks[0].x + Block.SIZE;
        blocks[2].y = blocks[0].y;

        blocks[3].x = blocks[0].x + Block.SIZE;
        blocks[3].y = blocks[0].y - Block.SIZE;

    }

    /*
     * ---b1---
     * ---b0---
     * ---b2-b3
     */
    public void updateDirection2() {
        tempBlocks[0].x = blocks[0].x;
        tempBlocks[0].y = blocks[0].y;

        tempBlocks[1].x = blocks[1].x + Block.SIZE;
        tempBlocks[1].y = blocks[1].y - Block.SIZE;

        tempBlocks[2].x = blocks[2].x - Block.SIZE;
        tempBlocks[2].y = blocks[2].y + Block.SIZE;

        tempBlocks[3].x = blocks[3].x;
        tempBlocks[3].y = blocks[3].y + 2 * Block.SIZE;

        updateXY(2);
    }

    /*
     * --------
     * b2-b0-b1
     * b3------
     */
    public void updateDirection3() {

        tempBlocks[0].x = blocks[0].x;
        tempBlocks[0].y = blocks[0].y;

        tempBlocks[1].x = blocks[1].x + Block.SIZE;
        tempBlocks[1].y = blocks[1].y + Block.SIZE;

        tempBlocks[2].x = blocks[2].x - Block.SIZE;
        tempBlocks[2].y = blocks[2].y - Block.SIZE;

        tempBlocks[3].x = blocks[3].x - 2 * Block.SIZE;
        tempBlocks[3].y = blocks[3].y;

        updateXY(3);
    }

    /*
     * b3-b2---
     * ---b0---
     * ---b1---
     */
    public void updateDirection4() {
        tempBlocks[0].x = blocks[0].x;
        tempBlocks[0].y = blocks[0].y;

        tempBlocks[1].x = blocks[1].x - Block.SIZE;
        tempBlocks[1].y = blocks[1].y + Block.SIZE;

        tempBlocks[2].x = blocks[2].x + Block.SIZE;
        tempBlocks[2].y = blocks[2].y - Block.SIZE;

        tempBlocks[3].x = blocks[3].x;
        tempBlocks[3].y = blocks[3].y - 2 * Block.SIZE;

        updateXY(4);
    }

    /*
     * ------b3
     * b1-b0-b2
     * --------
     */
    public void updateDirection1() {
        tempBlocks[0].x = blocks[0].x;
        tempBlocks[0].y = blocks[0].y;

        tempBlocks[1].x = blocks[1].x - Block.SIZE;
        tempBlocks[1].y = blocks[1].y - Block.SIZE;

        tempBlocks[2].x = blocks[2].x + Block.SIZE;
        tempBlocks[2].y = blocks[2].y + Block.SIZE;

        tempBlocks[3].x = blocks[3].x + 2*Block.SIZE;
        tempBlocks[3].y = blocks[3].y;

        updateXY(1);
    }

}
