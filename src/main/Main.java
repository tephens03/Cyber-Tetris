package main;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
    public static void main(String[] args) {
        final String BACKGROUND_IMAGE_URL = "/Users/transtephen/Desktop/Projects/Tetris/background.gif";

        JFrame window = new JFrame("Cyber Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gp = new GamePanel();
        window.add(gp);

        ImageIcon bgImage = new ImageIcon(BACKGROUND_IMAGE_URL);
        JLabel l = new JLabel(bgImage);
        window.add(l);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.pack();

    }
}