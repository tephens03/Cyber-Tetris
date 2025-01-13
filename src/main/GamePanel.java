package main;

import javax.swing.JPanel;
import java.awt.Dimension;

public class GamePanel extends JPanel {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setOpaque(false); // Makes the panel fully transparent
    }
}
