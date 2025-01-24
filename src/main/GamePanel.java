package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * The GamePanel class is the main panel for rendering and running the game.
 * It uses a game loop to update and render the game state at a consistent frame
 * rate.
 */
public class GamePanel extends JPanel implements Runnable {
    // Constants for panel dimensions
    public static final int WIDTH = 1280; // Width of the game panel in pixels
    public static final int HEIGHT = 720; // Height of the game panel in pixels

    private final int FPS = 60; // Target frame rate for the game loop
    private Thread gameThread; // Thread running the game loop
    private PlayManager pm; // Handles the game logic
    private KeyHandler kh; // Handles user keyboard input

    /**
     * Constructor for GamePanel. Initializes the panel's properties, key listener,
     * and game manager.
     */
    public GamePanel() {
        // Set the preferred size of the panel
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Set layout manager to null for custom positioning of components
        this.setLayout(null);

        // Make the panel fully transparent
        this.setOpaque(false);

        // Set bounds for the panel (same as preferred size)
        this.setBounds(0, 0, WIDTH, HEIGHT);

        // Initialize the key handler for capturing keyboard input
        kh = new KeyHandler();
        this.addKeyListener(kh); // Attach the key listener to this panel
        this.setFocusable(true); // Allow the panel to gain focus for key events
        this.setFocusTraversalKeysEnabled(false); // Disable focus traversal keys (e.g., Tab)

        // Initialize the game manager
        pm = new PlayManager();
    }

    /**
     * Starts the game loop by creating and starting a new thread.
     */
    public void start() {
        gameThread = new Thread(this); // Create a new thread with this class as the target
        gameThread.start(); // Start the thread, invoking the run() method
    }

    /**
     * The main game loop. Updates and renders the game state at a consistent frame
     * rate.
     */
    @Override
    public void run() {
        // Calculate the time interval for each frame (in nanoseconds)
        double drawInterval = 1000000000 / FPS;

        // Determine the time for the next frame to be rendered
        double nextDrawTime = System.nanoTime() + drawInterval;

        // Run the game loop while the thread is alive
        while (gameThread.isAlive()) {

            update(); // Update the game logic
            repaint(); // Request the panel to repaint (render the current state)

            try {
                // Calculate the remaining time before the next frame
                long remainingTime = (long) ((nextDrawTime - System.nanoTime()) / 1000000); // Convert to
                                                                                            // milliseconds

                // Sleep the thread for the remaining time to maintain the frame rate
                if (remainingTime > 0) {
                    Thread.sleep(remainingTime);
                }
            } catch (InterruptedException e) {
                // Log any interruption exceptions (e.g., during sleep)
                System.err.println(e);
            }

            // Update the time for the next frame
            nextDrawTime += drawInterval;
        }

    }

    /**
     * Updates the game state by delegating to the PlayManager.
     */
    public void update() {
        if (KeyHandler.pausePressed == false) {
            pm.update();
        }
    }

    /**
     * Paints the game components on the panel.
     *
     * @param g The Graphics object for drawing.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clear the panel before rendering

        // Cast Graphics to Graphics2D for more advanced rendering capabilities
        Graphics2D g2 = (Graphics2D) g;

        // Delegate the drawing to the PlayManager
        pm.draw(g2);
    }
}
