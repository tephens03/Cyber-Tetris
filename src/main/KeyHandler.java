package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyHandler class implements the KeyListener interface and handles keyboard inputs
 * for the game. It tracks the state of specific keys and updates their corresponding flags.
 */
public class KeyHandler implements KeyListener {
    // Flags for key states, used to track whether specific keys are currently pressed
    public static boolean upPressed, leftPressed, downPressed, rightPressed, pausePressed;

    /**
     * Called when a key is typed (pressed and released). This method is not used here
     * but must be implemented as part of the KeyListener interface.
     * 
     * @param e The KeyEvent triggered by the key typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // This method is not utilized in this implementation.
    }

    /**
     * Called when a key is pressed. Updates the state of relevant keys based on
     * the key code of the pressed key.
     * 
     * @param e The KeyEvent triggered by the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Get the key code of the pressed key
        int keyCode = e.getKeyCode();

        // Check which key was pressed and update the corresponding flag
        if (keyCode == KeyEvent.VK_W) { // W key pressed (up)
            upPressed = true;
        } else if (keyCode == KeyEvent.VK_A) { // A key pressed (left)
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_S) { // S key pressed (down)
            downPressed = true;
        } else if (keyCode == KeyEvent.VK_D) { // D key pressed (right)
            rightPressed = true;
        } else if (keyCode == KeyEvent.VK_SPACE) { // Space key pressed (pause toggle)
            pausePressed = !pausePressed; // Toggle pausePressed flag
        }
    }

    /**
     * Called when a key is released. Currently, this method does not implement any
     * specific behavior, but it can be extended if needed.
     * 
     * @param e The KeyEvent triggered by the key release.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Get the key code of the released key
        int keyCode = e.getKeyCode();

        // Check which key was released and reset the corresponding flag
        if (keyCode == KeyEvent.VK_W) { // W key released (up)
            upPressed = false;
        } else if (keyCode == KeyEvent.VK_A) { // A key released (left)
            leftPressed = false;
        } else if (keyCode == KeyEvent.VK_S) { // S key released (down)
            downPressed = false;
        } else if (keyCode == KeyEvent.VK_D) { // D key released (right)
            rightPressed = false;
        }
    }
}
