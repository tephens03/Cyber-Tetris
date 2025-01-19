package mino;

import java.awt.Color;
import java.awt.Rectangle;

import main.PlayManager;

public class Block extends Rectangle {
    // TODO: The game window will be calculated responsively later

    public static int MARGIN = 2;
    public static int SIZE = PlayManager.PLAYFIELD_WIDTH / 10;

    public int x, y;
    public Color color;

    public Block(Color color) {
        this.color = color;
    }

}
