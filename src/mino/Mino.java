package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import main.KeyHandler;
import main.PlayManager;

/**
 * The Mino class represents a single Tetrimino (block shape) in the Tetris
 * game.
 * It handles the blocks that form the mino, its movement, rotation, and
 * collision detection.
 * Each Mino consists of four blocks, and it provides functionality for updating
 * its position,
 * handling player input, and drawing the mino to the screen.
 */
abstract public class Mino {

    // Instance variables
    public Block[] blocks; // An array of blocks that make up the mino. A Tetrimino consists of 4 blocks.
    public Block[] tempBlocks; // Temporary array to hold the state of blocks before any transformation.
    private int autoDropCounter; // Counter to control the automatic downward movement of the mino.
    private int direction; // The direction the mino is currently facing (used for rotation).

    private boolean leftCollision; // Flag to check if the mino collides with the left boundary.
    private boolean rightCollision; // Flag to check if the mino collides with the right boundary.
    private boolean bottomCollision; // Flag to check if the mino collides with the bottom boundary.

    /**
     * Constructor to initialize a new Mino with a specified color.
     * Initializes the blocks and temporary blocks arrays and sets the initial
     * direction.
     *
     * @param c The color of the mino.
     */
    public Mino(Color c) {
        autoDropCounter = 0; // Initialize autoDropCounter to 0
        direction = 1; // Set the default direction
        blocks = new Block[4]; // Initialize blocks array (4 blocks per mino)
        tempBlocks = new Block[4]; // Initialize temporary blocks array (to store block state)

        // Initialize each block with the specified color
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(c);
            tempBlocks[i] = new Block(c);
        }
    }

    /**
     * Sets the position of the mino on the playfield.
     * Currently a placeholder method and can be expanded in the future.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void setXY(int x, int y) {
        // This method can be implemented to set the position of the mino
    }

    /**
     * Updates the position of the mino based on the current direction.
     * It copies the current position from tempBlocks to blocks.
     *
     * @param direction The new direction to rotate the mino.
     */
    public void updateXY(int direction) {
        this.direction = direction; // Set the current direction

        // Copy the current block positions to tempBlocks
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].x = tempBlocks[i].x;
            blocks[i].y = tempBlocks[i].y;
        }
    }

    /**
     * Updates the mino's state based on player input and collision detection.
     * Moves the mino left, right, or down and rotates it if necessary.
     */
    public void update() {
        int distanceToShift = 0; // Tracks how many pixels the mino will move (left or right)

        checkMovementCollision(); // Check for collisions before moving

        if (KeyHandler.upPressed) {
            System.out.println("Up Arrow-Key is pressed!");
            rotateMino(); // Rotate the mino if up arrow key is pressed
            KeyHandler.upPressed = false;
        } else if (KeyHandler.leftPressed) {
            System.out.println("Left Arrow-Key is pressed!");
            KeyHandler.leftPressed = false;
            if (!leftCollision) {
                distanceToShift = -Block.SIZE; // Move the mino left if there's no collision
            }
        } else if (KeyHandler.downPressed && !bottomCollision) {
            System.out.println("Down Arrow-Key is pressed!");
            KeyHandler.downPressed = false;
            autoDropCounter = PlayManager.DROP_INTERVAL - 1; // Drop the mino down faster
        } else if (KeyHandler.rightPressed) {
            System.out.println("Right Arrow-Key is pressed!");
            KeyHandler.rightPressed = false;
            if (!rightCollision) {
                distanceToShift = Block.SIZE; // Move the mino right if there's no collision
            }
        }

        // Move the mino left or right if there's no collision
        if (distanceToShift != 0) {
            for (Block block : blocks) {
                block.x += distanceToShift;
            }
        }

        // Handle automatic downward movement if it's time
        if (++autoDropCounter == PlayManager.DROP_INTERVAL && !bottomCollision) {
            for (Block block : blocks) {
                block.y += Block.SIZE; // Move the blocks down by one block size
            }
            autoDropCounter = 0; // Reset the counter after moving the mino down
        }
    }

    /**
     * Draws the mino to the screen by iterating through its blocks.
     * Each block is drawn with its respective color.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g2) {
        for (Block block : blocks) {
            g2.setColor(Color.WHITE);
            g2.fillRect(block.x, block.y, Block.SIZE, Block.SIZE); // Draw the outline
            g2.setColor(blocks[0].color);
            g2.fillRect(block.x + Block.MARGIN, block.y + Block.MARGIN, Block.SIZE - (2 * Block.MARGIN),
                    Block.SIZE - (2 * Block.MARGIN)); // Fill the block with its color
        }
    }

    /**
     * Rotates the mino based on the current direction.
     * Each direction corresponds to a different block arrangement.
     */
    public void rotateMino() {
        switch (direction) {
            case 1:
                updateDirection2();
                break;
            case 2:
                updateDirection3();
                break;
            case 3:
                updateDirection4();
                break;
            default:
                updateDirection1();
                break;
        }
    }

    /**
     * Checks for collisions with the left, right, and bottom edges of the
     * playfield.
     * Sets the appropriate flags (leftCollision, rightCollision, bottomCollision)
     * based on the current position of the blocks.
     */
    public void checkMovementCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        for (Block block : blocks) {
            if (block.x + Block.SIZE == PlayManager.playfield_x + PlayManager.PLAYFIELD_WIDTH) {
                rightCollision = true;
            }
            if (block.x == PlayManager.playfield_x) {
                leftCollision = true;
            }
            if (block.y + Block.SIZE == PlayManager.playfield_y + PlayManager.PLAYFIELD_HEIGHT) {
                bottomCollision = true;
            }
        }
    }

    /**
     * Placeholder method for updating the mino's state when it's in direction 1.
     */
    public void updateDirection1() {
        // Logic for direction 1 (default)
    }

    /**
     * Placeholder method for updating the mino's state when it's in direction 2.
     */
    public void updateDirection2() {
        // Logic for direction 2
    }

    /**
     * Placeholder method for updating the mino's state when it's in direction 3.
     */
    public void updateDirection3() {
        // Logic for direction 3
    }

    /**
     * Placeholder method for updating the mino's state when it's in direction 4.
     */
    public void updateDirection4() {
        // Logic for direction 4
    }
}
