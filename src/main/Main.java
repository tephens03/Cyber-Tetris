package main;

import java.awt.Dimension;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Cyber Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // Use a JLayeredPane for explicit layering
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280, 720));

        // Create and add background image
        BackgroundImage bgImage = new BackgroundImage();
        bgImage.setBounds(0, 0, 1280, 720);
        layeredPane.add(bgImage, JLayeredPane.DEFAULT_LAYER); // Add to the default layer

        // Create and add game panel
        GamePanel gp = new GamePanel();
        gp.setBounds(0, 0, 1280, 720);
        layeredPane.add(gp, JLayeredPane.PALETTE_LAYER); // Add to a higher layer

        // Add the layered pane to the JFrame
        window.add(layeredPane);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.start();
    }
}
