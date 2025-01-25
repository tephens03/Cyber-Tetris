package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import mino.*;

/**
 * The PlayManager class handles the logic and rendering for the game.
 * It manages the playfield, the Heads-Up Display (HUD), and the active mino.
 */
public class PlayManager {

    // Constants for the playfield and HUD dimensions
    public static final int PLAYFIELD_WIDTH = 300; // Width of the playfield in pixels
    public static final int PLAYFIELD_HEIGHT = 600; // Height of the playfield in pixels
    public static final int HUD_SIDE = 200; // Size of the HUD square in pixels
    public static final int DROP_INTERVAL = 60; // Frames before a mino drops automatically

    // Coordinates for playfield and HUD
    public static int playfield_x; // X-coordinate of the playfield's top-left corner
    public static int playfield_y; // Y-coordinate of the playfield's top-left corner
    private static int hud_x; // X-coordinate of the HUD's top-left corner
    private static int hud_y; // Y-coordinate of the HUD's top-left corner

    // The currently active mino
    public Mino currentMino;
    public Mino nextMino;
    public static ArrayList<Mino> minos;

    // Coordinates for spawning new minos
    private final int MINO_START_Y; // Starting Y-coordinate for current minos
    private final int MINO_START_X; // Starting X-coordinate for current minos
    private final int HUD_MINO_START_Y; // Starting Y-coordinate for next mino in HUD box
    private final int HUD_MINO_START_X; // Starting X-coordinate for next mino in HUD box

    /**
     * Constructor for PlayManager. Initializes the playfield, HUD positions,
     * and the first active mino.
     */
    public PlayManager() {
        minos = new ArrayList<Mino>();
        // Calculate the X-coordinate for centering the playfield horizontally
        playfield_x = (GamePanel.WIDTH / 2) - (PLAYFIELD_WIDTH / 2);

        // Set the Y-coordinate for the playfield with a fixed top margin
        playfield_y = 50; // 50px margin from the top

        // Determine the spawn position for minos
        MINO_START_Y = playfield_y + Block.SIZE; // Slightly below the playfield's top
        MINO_START_X = (PLAYFIELD_WIDTH / 2) + playfield_x - Block.SIZE; // Centered horizontally

        // Calculate the HUD's position relative to the playfield
        hud_x = playfield_x + PLAYFIELD_WIDTH + 100; // 100px gap to the right of the playfield
        hud_y = playfield_y + PLAYFIELD_HEIGHT - HUD_SIDE; // Align with the playfield bottom

        HUD_MINO_START_X = (HUD_SIDE / 2) + hud_x - Block.SIZE; // Centered horizontally
        HUD_MINO_START_Y = hud_y + (HUD_SIDE / 2) + Block.SIZE;

        // Initialize the first mino and set its spawn position
        currentMino = pickRandomMino();
        currentMino.setXY(MINO_START_X, MINO_START_Y);

        nextMino = pickRandomMino();
        nextMino.setXY(HUD_MINO_START_X, HUD_MINO_START_Y);

        // minos.add(currentMino);
    }

    /**
     * Updates the game logic, such as the position of the current mino.
     */
    public void update() {
        if (currentMino.active) {
            currentMino.update();
        } else {
            minos.add(currentMino);

            currentMino = nextMino;
            currentMino.setXY(MINO_START_X, MINO_START_Y);


            nextMino = pickRandomMino();
            nextMino.setXY(HUD_MINO_START_X, HUD_MINO_START_Y);

        }

    }

    /**
     * Draws the playfield, HUD, and current mino on the screen.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    public void draw(Graphics2D g2) {

        // Render the playfield background
        Color color = new Color(0, 0, 0, 100); // Semi-transparent black
        g2.setColor(color);
        g2.fillRect(playfield_x, playfield_y, PLAYFIELD_WIDTH, PLAYFIELD_HEIGHT); // Playfield area
        g2.fillRect(hud_x, hud_y, HUD_SIDE, HUD_SIDE); // HUD area

        // Render borders for the playfield and HUD
        color = new Color(0, 0, 0, 180); // Darker black for borders
        g2.setColor(color);
        g2.setStroke(new BasicStroke(6f)); // Border thickness
        g2.drawRect(playfield_x - 3, playfield_y - 3, PLAYFIELD_WIDTH + 6, PLAYFIELD_HEIGHT + 6); // Playfield border
        g2.drawRect(hud_x - 3, hud_y - 3, HUD_SIDE + 6, HUD_SIDE + 6); // HUD border

        // Render HUD label text
        color = new Color(255, 255, 255); // White color for text
        g2.setColor(color);
        g2.setFont(new Font("Ariel", Font.PLAIN, 30)); // Set font and size
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // Enable smoother text rendering
        g2.drawString("NEXT", hud_x + 60, hud_y + 60); // Draw "NEXT" label for the HUD

        // Render the current mino
        currentMino.draw(g2);

        nextMino.draw(g2);

        for (Mino mino : minos) {
            mino.draw(g2);
        }

        // Draw Pause
        if (KeyHandler.pausePressed == true) {
            g2.setColor((Color.yellow));
            g2.setFont(g2.getFont().deriveFont(50f));
            g2.drawString("PAUSED", playfield_x + 60, playfield_y + 300);
        }
    }

    /**
     * Randomly selects one of the seven possible mino shapes.
     *
     * @return A randomly chosen mino object.
     */
    public Mino pickRandomMino() {
        // Generate a random number between 0 and 6 (inclusive)
        int option = new Random().nextInt(7);
        Mino mino = null;

        // Instantiate a mino based on the random number
        switch (option) {
            case 1:
                // mino = new Mino_I(); // Straight line mino
                mino = new Mino_J(); // J-shaped mino
                break;
            case 2:
                mino = new Mino_J(); // J-shaped mino
                break;
            case 3:
                mino = new Mino_L(); // L-shaped mino
                break;
            case 4:
                mino = new Mino_O(); // Square-shaped mino
                break;
            case 5:
                mino = new Mino_S(); // S-shaped mino
                break;
            case 6:
                mino = new Mino_T(); // T-shaped mino
                break;
            default:
                mino = new Mino_Z(); // Z-shaped mino (default case)
                break;
        }
        return mino;
    }
}
