package main;

import java.awt.Dimension;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Define the window's width and height
        final int WIDTH = 1280, HEIGHT = 720;

        // Create a JFrame object which represents the main window of the game
        JFrame window = new JFrame("Cyber Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        window.setResizable(false); // Disable window resizing

        // Use a JLayeredPane to layer background and the actual game
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Set the preferred size of the layered pane

        // Create and add the background image component
        BackgroundImage bgImage = new BackgroundImage(WIDTH, HEIGHT);
        layeredPane.add(bgImage, JLayeredPane.DEFAULT_LAYER); // Add background image to the default layer (bottom layer)

        // Create and add the game panel component
        GamePanel gp = new GamePanel();
        layeredPane.add(gp, JLayeredPane.PALETTE_LAYER); // Add the game panel to a higher layer than the background

        // Start the game on the game panel
        gp.start();

        // Add the layered pane to the window
        window.add(layeredPane);

        // Force every component to actually fit the preferred size
        window.pack();

        // Center the game window on the screen
        window.setLocationRelativeTo(null);

        // Make the game window visible
        window.setVisible(true);
    }
}
