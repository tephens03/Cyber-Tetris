package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackgroundImage extends JLabel {
    final private String BACKGROUND_IMAGE_URL = "/Users/transtephen/Desktop/Projects/Tetris/background.gif";

    public BackgroundImage() {
        ImageIcon bgImage = new ImageIcon(BACKGROUND_IMAGE_URL);
        this.setIcon(bgImage);
        this.setBounds(0, 0, 1280, 720);

    }
}
