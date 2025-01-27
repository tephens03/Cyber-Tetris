package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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

    // Coordinates for playfield and HUD
    public static int playfield_x; // X-coordinate of the playfield's top-left corner
    public static int playfield_y; // Y-coordinate of the playfield's top-left corner
    private static int mino_hud_x; // X-coordinate of the HUD's bottom-right corner
    private static int mino_hud_y; // Y-coordinate of the HUD's bottom-right corner
    private static int level_hud_x; // X-coordinate of the HUD's bottom-right corner
    private static int level_hud_y; // Y-coordinate of the HUD's bottom-right corner

    private int score; //
    private int previousScoreThreshold; //
    private int level; //
    public static int drop_interval = 60; // Frames before a mino drops automatically

    // The currently active mino
    private Mino currentMino;
    private Mino nextMino;
    private static LinkedList<Mino> minos;
    public static LinkedList<Block> blocks;

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
        level = 1;
        blocks = new LinkedList<Block>();
        minos = new LinkedList<Mino>();
        previousScoreThreshold = score = 0;
        // Calculate the X-coordinate for centering the playfield horizontally
        playfield_x = (GamePanel.WIDTH / 2) - (PLAYFIELD_WIDTH / 2);

        // Set the Y-coordinate for the playfield with a fixed top margin
        playfield_y = 50; // 50px margin from the top

        // Determine the spawn position for minos
        MINO_START_Y = playfield_y + Block.SIZE; // Slightly below the playfield's top
        MINO_START_X = (PLAYFIELD_WIDTH / 2) + playfield_x - Block.SIZE; // Centered horizontally

        // Calculate the HUD's position relative to the playfield
        mino_hud_x = playfield_x + PLAYFIELD_WIDTH + 100; // 100px gap to the right of the playfield
        mino_hud_y = playfield_y + PLAYFIELD_HEIGHT - HUD_SIDE; // Align with the playfield bottom

        HUD_MINO_START_X = (HUD_SIDE / 2) + mino_hud_x - Block.SIZE; // Centered horizontally
        HUD_MINO_START_Y = mino_hud_y + (HUD_SIDE / 2) + Block.SIZE;

        // Calculate the top HUD's position relative to the playfield
        level_hud_x = playfield_x + PLAYFIELD_WIDTH + 100;// 100px gap to the right of the playfield
        level_hud_y = playfield_y + 100; // 100px below the playfield

        // Initialize the first mino and set its spawn position
        currentMino = pickRandomMino();
        currentMino.setXY(MINO_START_X, MINO_START_Y);

        nextMino = pickRandomMino();
        nextMino.setXY(HUD_MINO_START_X, HUD_MINO_START_Y);

    }

    /**
     * Updates the game difficulty based on the player's score.
     * 
     * The difficulty is determined by how much the player has scored since the last
     * difficulty increase.
     * Every time the player's score surpasses a threshold of 500 points above the
     * previous threshold.
     * the drop interval is reduced, making the blocks fall faster, and the level is
     * incremented.
     * 
     * The drop interval is set to a minimum value of 10, ensuring that blocks fall
     * at a reasonable rate.
     */
    public void updateDifficulty() {
        // Check if the drop interval is not already at the minimum and if the score has
        // exceeded the threshold
        if (drop_interval != 10 && score - previousScoreThreshold > 500) {
            // Update the previous score threshold
            previousScoreThreshold = score;

            // Decrease the drop interval to make blocks fall faster
            drop_interval -= 5;

            // Increase the level as the game becomes more difficult
            level += 1;
        }
    }

    /**
     * Updates the game logic by handling the movement and placement of the current
     * mino.
     * 
     * The method first checks if the current mino is still active:
     * - If it is, it updates the mino's state and position.
     * - If it's not active (meaning it has landed or collided), it adds the mino to
     * the list of minos,
     * and also adds its blocks to the blocks list, marking them as part of the
     * playfield.
     * 
     * After placing the mino, the method checks if the new mino is overlapping with
     * the starting position,
     * which indicates a game over condition. If this happens, the game exits.
     * 
     * If the game is not over, the method:
     * - Adds points for the newly placed mino.
     * - Sets the next mino as the current one, and spawns a new next mino.
     * - Checks for rows that should be cleared.
     * - Updates the game difficulty based on the current score.
     */
    public void update() {
        // Check if the current mino is still active
        if (currentMino.active) {

            // If active, update its state and position
            currentMino.update();

        } else {

            // If not active, add the current mino to the minos list (indicating it has
            // landed)
            minos.add(currentMino);

            // Add the blocks of the current mino to the blocks list
            for (int i = 0; i < currentMino.blocks.length; i++) {
                blocks.add(currentMino.blocks[i]);
            }

            // Check if the new mino's blocks overlap with the spawn position, indicating a
            // game over
            if (currentMino.blocks[0].x == MINO_START_X && currentMino.blocks[0].y == MINO_START_Y) {
                System.out.println("GAME OVER");
                System.exit(0); // Terminate the game if game over
            } else {
                // Add points for placing the mino
                addScoresForMinoPlacement();

                // Set the next mino as the current mino and reset its position
                currentMino = nextMino;
                currentMino.setXY(MINO_START_X, MINO_START_Y);

                // Generate a new next mino and set its position in the HUD
                nextMino = pickRandomMino();
                nextMino.setXY(HUD_MINO_START_X, HUD_MINO_START_Y);

                // Check if any rows can be cleared
                checkDeleteRow();

                // Update the game difficulty based on the current score
                updateDifficulty();
            }
        }
    }

    /**
     * Adds points to the score for successfully placing a mino on the playfield.
     * This method adds a base of 50 points whenever a mino is placed on the field.
     */
    public void addScoresForMinoPlacement() {
        score += 50; // Base points for placing a mino
    }

    /**
     * Adds points to the score based on the number of rows cleared.
     * 
     * @param rowClear The number of rows cleared (1 to 4).
     *                 Adds points based on the number of rows cleared, with
     *                 increasing points for clearing multiple rows at once.
     *                 - 1 row cleared: 100 points
     *                 - 2 rows cleared: 250 points
     *                 - 3 rows cleared: 400 points
     *                 - 4 rows cleared (Tetris): 800 points
     */
    public void addScoresByRowClear(int rowClear) {
        int points = 0;

        switch (rowClear) {
            case 1:
                points = 100; // 1 row cleared
                break;
            case 2:
                points = 250; // 2 rows cleared (double)
                break;
            case 3:
                points = 400; // 3 rows cleared (triple)
                break;
            case 4:
                points = 800; // 4 rows cleared (tetris)
                break;
            default:
                // No points if no row is cleared
                points = 0;
                break;
        }

        // Add points to the current score
        score += points;
    }

    /**
     * Checks and deletes any full rows on the playfield.
     * Clears the blocks of full rows and shifts the remaining rows down.
     */
    public void checkDeleteRow() {
        int x = playfield_x;
        int y = playfield_y;

        int minoCount = 0;
        int rowClear = 0;

        Map<String, Boolean> blocksToDelete = new HashMap<>();
        LinkedList<Block> blocksToShift = new LinkedList<>();

        // Iterate through each row of the playfield
        while (x < playfield_x + PLAYFIELD_WIDTH && y < playfield_y + PLAYFIELD_HEIGHT) {

            for (int i = 0; i < blocks.size(); i++) {
                Block currentBlock = blocks.get(i);
                if (currentBlock.x == x && currentBlock.y == y) {
                    // Mark blocks to be deleted
                    blocksToDelete.put(currentBlock.x + "-" + currentBlock.y, true);
                    blocksToShift.add(currentBlock);
                    minoCount++;
                    break;
                }
            }

            // Move to the next column
            x += Block.SIZE;

            // If the end of a row is reached, check if it should be cleared
            if (x >= playfield_x + PLAYFIELD_WIDTH) {
                if (minoCount == 10) {
                    // Clear the full row and shift remaining blocks down
                    deleteRow(blocksToDelete);
                    shiftRowDown(blocksToShift);
                    rowClear++;
                }
                // Reset for the next row
                blocksToDelete.clear();
                x = playfield_x;
                y += Block.SIZE;
                minoCount = 0;
            }

        }
        // Add points for the number of rows cleared
        addScoresByRowClear(rowClear);
    }

    /**
     * Deletes the blocks marked for deletion from the blocks list.
     * 
     * @param blocksToDelete A map containing the blocks to be removed from the
     *                       playfield.
     */
    public void deleteRow(Map<String, Boolean> blocksToDelete) {
        // Iterate through the blocks and remove those marked for deletion
        for (int i = 0; i < blocks.size(); i++) {
            String id = blocks.get(i).x + "-" + blocks.get(i).y;
            if (blocksToDelete.containsKey(id)) {
                blocks.remove(i);
                i--; // Adjust the index after removal
            }
        }
    }

    /**
     * Shifts the rows down after clearing a full row.
     * 
     * @param blocksToShift The list of blocks that need to be moved down after row
     *                      deletion.
     */
    public void shiftRowDown(LinkedList<Block> blocksToShift) {
        // Remove the last 10 elements (the blocks in the deleted row)
        for (int i = 0; i < 10; i++) {
            blocksToShift.removeLast(); // Continuously removes the last element
        }

        // Shift the remaining blocks down by one row
        for (Block block : blocksToShift) {
            block.y += Block.SIZE;
        }
    }

    /**
     * Renders the game components on the screen, including the playfield, HUD,
     * current and next mino, and the game state.
     * 
     * This method draws the following:
     * 1. The playfield background with a semi-transparent black color.
     * 2. The borders of the playfield and HUD areas.
     * 3. The "NEXT" and "LEVEL" labels with the corresponding values.
     * 4. The current score.
     * 5. The current mino and the next mino.
     * 6. The blocks that have already been placed in the game.
     * 7. If the game is paused, it displays a "PAUSED" message in the center of the
     * playfield.
     * 
     * @param g2 The Graphics2D object used for rendering, allowing to draw shapes
     *           and text on the screen.
     */
    public void draw(Graphics2D g2) {

        // Render the playfield background
        Color color = new Color(0, 0, 0, 100); // Semi-transparent black for background
        g2.setColor(color);
        g2.fillRect(playfield_x, playfield_y, PLAYFIELD_WIDTH, PLAYFIELD_HEIGHT); // Draw playfield
        g2.fillRect(mino_hud_x, mino_hud_y, HUD_SIDE, HUD_SIDE); // Draw HUD area for mino preview
        g2.fillRect(level_hud_x, level_hud_y, HUD_SIDE, HUD_SIDE); // Draw HUD area for level display

        // Render borders for the playfield and HUD
        color = new Color(0, 0, 0, 180); // Darker black for borders
        g2.setColor(color);
        g2.setStroke(new BasicStroke(6f)); // Set stroke thickness for borders
        g2.drawRect(playfield_x - 3, playfield_y - 3, PLAYFIELD_WIDTH + 6, PLAYFIELD_HEIGHT + 6); // Playfield border
        g2.drawRect(mino_hud_x - 3, mino_hud_y - 3, HUD_SIDE + 6, HUD_SIDE + 6); // HUD border for mino preview
        g2.drawRect(level_hud_x - 3, level_hud_y - 3, HUD_SIDE + 6, HUD_SIDE + 6); // HUD border for level

        // Render HUD label text (next mino and level)
        color = new Color(255, 255, 255); // White color for text
        g2.setColor(color);
        g2.setFont(new Font("Ariel", Font.PLAIN, 30)); // Set font for labels

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // Smooth
                                                                                                           // text
        g2.drawString("NEXT", mino_hud_x + 60, mino_hud_y + 60); // Label for the next mino
        g2.drawString("LEVEL " + level, level_hud_x + 45, level_hud_y + 60); // Display the current level
        g2.drawString(Integer.toString(score), level_hud_x + 75, level_hud_y + 120); // Display current score

        // Render the current and next minos
        currentMino.draw(g2); // Draw the current mino
        nextMino.draw(g2); // Draw the next mino

        // Render all blocks that have been placed on the playfield
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).draw(g2); // Draw each block
        }

        // Render the pause message if the game is paused
        if (KeyHandler.pausePressed) {
            g2.setColor(Color.YELLOW); // Yellow color for the pause message
            g2.setFont(g2.getFont().deriveFont(50f)); // Larger font for pause message
            g2.drawString("PAUSED", playfield_x + 60, playfield_y + 300); // Display "PAUSED" message
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
                mino = new Mino_Z(); // Z-shaped mino (default case)
                break;
            case 2:
                mino = new Mino_J(); // J-shaped mino
                break;
            case 3:
                mino = new Mino_L(); // L-shaped mino
                break;
            case 4:
                mino = new Mino_I(); // Square-shaped mino
                break;
            case 5:
                mino = new Mino_S(); // S-shaped mino
                break;
            case 6:
                mino = new Mino_O(); // Z-shaped mino (default case)
                break;
            default:
                mino = new Mino_T(); // T-shaped mino
                break;
        }
        return mino;
    }

}
