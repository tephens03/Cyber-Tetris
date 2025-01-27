package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import main.KeyHandler;
import main.PlayManager;

/**
 * The Mino class represents a single Tetrimino (block shape) in the Tetris
 * game. It handles the blocks that form the mino, its movement, rotation, and
 * collision detection. Each Mino consists of four blocks, and it provides
 * functionality for updating its position, handling player input, and drawing
 * the mino to the screen.
 */
abstract public class Mino {

    // Instance variables
    public Block[] blocks; // An array of blocks that make up the mino. A Tetrimino consists of 4 blocks.
    public Block[] tempBlocks; // Temporary array to hold the state of blocks before any transformation.
    public boolean active; // A boolean variable to indicate if mino is still moveable.
    private int autoDropCounter; // Counter to control the automatic downward movement of the mino.
    private int deactivateMinoCounter; // Counter to control the deactivation of the mino after touching the bottom.
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
        active = true;
        deactivateMinoCounter = 0;
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

        checkRotateCollision(); // Check for collisions before rotating
        if (!leftCollision && !rightCollision && !bottomCollision) {
            this.direction = direction; // Set the current direction

            // Copy the current block positions to tempBlocks
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].x = tempBlocks[i].x;
                blocks[i].y = tempBlocks[i].y;
            }
        }
    }

    /**
     * Updates the mino's state based on player input and collision detection.
     * Moves the mino left, right, or down and rotates it if necessary.
     */
    public void update() {

        int distanceToShift = 0; // Tracks how many pixels the mino will move (left or right)

        deactivateMino(); // Deactivate mino if it reaches the bottom

        checkMovementCollision(); // Check for collisions before moving

        // Handle key press for rotation
        if (KeyHandler.upPressed) {
            rotateMino(); // Rotate the mino if up arrow key is pressed
            KeyHandler.upPressed = false;

        } else if (KeyHandler.leftPressed) {
            // Move the mino left if there's no collision
            KeyHandler.leftPressed = false;
            if (!leftCollision) {
                distanceToShift = -Block.SIZE; // Move the mino left by one block size
            }
        } else if (KeyHandler.downPressed) {
            // Move the mino down if there's no collision
            KeyHandler.downPressed = false;
            if (!bottomCollision) {
                for (Block block : blocks) {
                    block.y += Block.SIZE; // Move the blocks down by one block size
                }
                autoDropCounter = 0;
                return;
            }
        } else if (KeyHandler.rightPressed) {
            // Move the mino right if there's no collision
            KeyHandler.rightPressed = false;
            if (!rightCollision) {
                distanceToShift = Block.SIZE; // Move the mino right by one block size
            }
        }

        // Move the mino left or right if there's no collision
        if (distanceToShift != 0) {
            for (Block block : blocks) {
                block.x += distanceToShift;
            }
        }

        // Handle automatic downward movement if it's time
        autoDropCounter++;
        if (!bottomCollision && autoDropCounter == PlayManager.drop_interval) {
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
            block.draw(g2); // Draw each block in the mino
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
     * playfield for natural movement.
     * Sets the appropriate flags (leftCollision, rightCollision, bottomCollision)
     * based on the current position of the blocks.
     */
    public void checkMovementCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        checkExistedMinoCollision(); // Check for collisions with other minos

        // Check for boundary collisions with the playfield edges
        for (Block block : blocks) {
            if (block.x + Block.SIZE == PlayManager.playfield_x + PlayManager.PLAYFIELD_WIDTH) {
                rightCollision = true;
            }
            if (block.x == PlayManager.playfield_x) {
                leftCollision = true;
            }
            if (block.y + Block.SIZE == PlayManager.playfield_y + PlayManager.PLAYFIELD_HEIGHT) {
                bottomCollision = true; // Mino hits the bottom
            }
        }
    }

    /**
     * Deactivates the mino after it touches the bottom of the playfield.
     * After 15 frames, it sets the 'active' flag to false.
     */
    public void deactivateMino() {
        if (bottomCollision) {
            deactivateMinoCounter++; // Increment counter after bottom collision
        }
        if (deactivateMinoCounter == 15) {
            active = false; // Deactivate the mino after 15 frames
        }
    }

    /**
     * Checks for collisions with other existing minos in the playfield.
     * Sets the appropriate flags for left, right, or bottom collision.
     */
    public void checkExistedMinoCollision() {
        for (Block block : blocks) {
            for (Block existedBlock : PlayManager.blocks) {
                if (block.x + Block.SIZE == existedBlock.x && block.y == existedBlock.y) {
                    rightCollision = true;
                }
                if (block.x - Block.SIZE == existedBlock.x && block.y == existedBlock.y) {
                    leftCollision = true;
                }
                if (block.y + Block.SIZE == existedBlock.y && block.x == existedBlock.x) {
                    bottomCollision = true;
                }
            }
        }
    }

    /**
     * Checks for collisions with the left, right, and bottom edges of the
     * playfield when rotating the mino.
     * Sets the appropriate flags based on the current position of the blocks.
     */
    public void checkRotateCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        // Check the rotation of the mino by examining tempBlocks
        for (Block block : tempBlocks) {
            if (block.x >= PlayManager.playfield_x + PlayManager.PLAYFIELD_WIDTH) {
                rightCollision = true;
            }
            if (block.x < PlayManager.playfield_x) {
                leftCollision = true;
            }
            if (block.y >= PlayManager.playfield_y + PlayManager.PLAYFIELD_HEIGHT) {
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
