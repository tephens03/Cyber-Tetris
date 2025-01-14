package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackgroundImage extends JLabel {
    final private String BACKGROUND_IMAGE_URL = "/Users/transtephen/Desktop/Projects/Tetris/background.gif";
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public BackgroundImage() {
        ImageIcon bgImage = new ImageIcon(BACKGROUND_IMAGE_URL);
        this.setIcon(bgImage);

        this.setBounds(100, 50, 100, 100);

    }
}
