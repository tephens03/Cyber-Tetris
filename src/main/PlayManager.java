package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PlayManager {
    private final int WIDTH = 360;
    private final int HEIGHT = 600;
    private static int left_x;
    private static int right_x;
    private static int top_y;
    private static int botom_y;

    public PlayManager() {
        super();
        left_x = (GamePanel.WIDTH / 2) - (WIDTH / 2);
        right_x = left_x + WIDTH;
        top_y = 50;
        botom_y = top_y + HEIGHT;
    }

    public void update() {
    }

    public void draw(Graphics2D g2) {
        // Draw the game border
        Color color = new Color(255, 255, 255, 140);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(6f)); // Set width of the border
        // Since border-width = 6, we push the coordinate 6px backward, we want to keep
        // the content inside the rect 600x360 so we include the double border width
        g2.drawRect(left_x - 6, top_y - 6, WIDTH + 12, HEIGHT + 12);
        int x = right_x + 100;
        int y = botom_y - 200;
        g2.drawRect(x, y, 200, 200); // Draw the score menu border

        // Fill the gamezone background
        color = new Color(255, 255, 255, 100);
        g2.setColor(color);
        g2.fillRect(left_x, top_y, WIDTH, HEIGHT);
        // Fill the score menu background
        g2.fillRect(x + 6, y + 6, 200 - 12, 200 - 12);

        // Render Text
        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setFont(new Font("Ariel", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 60, y + 60);

    }
}
