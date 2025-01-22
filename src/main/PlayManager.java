package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import mino.Block;
import mino.Mino;
import mino.Mino_J;
import mino.Mino_L;

public class PlayManager {
    // TODO: The game window will be calculated responsively later
    public static final int PLAYFIELD_WIDTH = 300;
    public static final int PLAYFIELD_HEIGHT = 600;
    public static final int HUD_SIDE = 200; // only 1 value because its a square
    public static final int DROP_INTERVAL = 60;

    private static int playfield_x;
    private static int playfield_y;
    private static int hud_x;
    private static int hud_y;

    public Mino currentMino;
    private final int MINO_START_Y;
    private final int MINO_START_X;

    public PlayManager() {

        // Find middle point of the window, set it back half the playfield width
        // allowing it to perfectly center (to the window)
        playfield_x = (GamePanel.WIDTH / 2) - (PLAYFIELD_WIDTH / 2);

        // Hard-code y of the playfield, leaving a distance of 50 pixel from the top
        playfield_y = 50;

        // HUD will be 100px away from playfield to the right
        hud_x = playfield_x + PLAYFIELD_WIDTH + 100;

        // HUD will align with playfield, its y will be HUD_SIDE(px) away from the
        // playfield bottom
        hud_y = playfield_y + PLAYFIELD_HEIGHT - HUD_SIDE;

        currentMino = new Mino_J();
        // Calculate spawning coordinate of each Mino
        MINO_START_Y = playfield_y + Block.SIZE;
        MINO_START_X = (PLAYFIELD_WIDTH / 2) + playfield_x - Block.SIZE;
        currentMino.setXY(MINO_START_X, MINO_START_Y);

    }

    public void update() {

        currentMino.update();
    }

    public void draw(Graphics2D g2) {
        /*
         * // Draw the game border
         * Color color = new Color(255, 255, 255, 140);
         * g2.setColor(color);
         * g2.setStroke(new BasicStroke(6f)); // Set width of the border
         * // Since border-width = 6, we push the coordinate 6px backward, we want to
         * keep
         * // the content inside the rect 600x360 so we include the double border width
         * g2.drawRect(left_x - 6, top_y - 6, WIDTH + 12, HEIGHT + 12);
         * int x = right_x + 100;
         * int y = botom_y - 200;
         * g2.drawRect(x, y, 200, 200); // Draw the score menu border
         * 
         * // Fill the gamezone background
         * color = new Color(255, 255, 255, 100);
         * g2.setColor(color);
         * g2.fillRect(left_x, top_y, WIDTH, HEIGHT);
         * // Fill the score menu background
         * g2.fillRect(x + 6, y + 6, 200 - 12, 200 - 12);
         * 
         * // Render Text
         * color = new Color(255, 255, 255);
         * g2.setColor(color);
         * g2.setFont(new Font("Ariel", Font.PLAIN, 30));
         * g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
         * RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         * g2.drawString("NEXT", x + 60, y + 60);
         */
        // Render playfield and HUD area
        Color color = new Color(0, 0, 0, 100);
        g2.setColor(color);
        g2.fillRect(playfield_x, playfield_y, PLAYFIELD_WIDTH, PLAYFIELD_HEIGHT);
        g2.fillRect(hud_x, hud_y, HUD_SIDE, HUD_SIDE);

        // Render playfield and HUD border
        color = new Color(0, 0, 0, 180);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(6f)); // Set width of the border
        g2.drawRect(playfield_x - 3, playfield_y - 3, PLAYFIELD_WIDTH + 6, PLAYFIELD_HEIGHT + 6);
        g2.drawRect(hud_x - 3, hud_y - 3, HUD_SIDE + 6, HUD_SIDE + 6);

        // Render HUD text
        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setFont(new Font("Ariel", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", hud_x + 60, hud_y + 60);

        currentMino.draw(g2);

    }
}
