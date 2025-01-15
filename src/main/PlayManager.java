package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

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
        Color color = new Color(0, 0, 0, 125);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(6f)); // Set width of the border
        g2.drawRect(left_x - 6, top_y - 6, WIDTH + 12, HEIGHT + 12); // Since border-width = 6, we push the coordinate
                                                                     // 6pixel backward, we want to keep the content
                                                                     // inside the rect 600x360 so we include the double
                                                                     // border width

        // Draw the score menu border
        int x = right_x + 100;
        int y = (botom_y - 200) + 6;
        g2.drawRect(x, y, 200, 200);

        // Fill the gamezone background
        color = new Color(0, 0, 0, 75);
        g2.setColor(color);
        g2.fillRect(left_x - 3, top_y - 3, WIDTH + 6, HEIGHT + 6);

        // Fill the score menu background
        g2.fillRect(x + 3, y + 3, 200 - 6, 200 - 6);

        // g2.dispose();
    }
}
