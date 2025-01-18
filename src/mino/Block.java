package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle {

    public int x, y;
    public static int SIZE=36;
    public Color color;

    public Block(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect(x, y, SIZE, SIZE);
    }

}
