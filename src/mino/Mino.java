package mino;

import java.awt.Color;

public class Mino {
    Block[] blocks = new Block[4];

    public Mino() {
        super();
    }

    public void create(Color c) {

        blocks[0] = new Block(c);
        blocks[1] = new Block(c);
        blocks[2] = new Block(c);
        blocks[3] = new Block(c);

    }
}
