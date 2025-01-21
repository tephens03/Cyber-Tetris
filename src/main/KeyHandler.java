package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public static boolean upPressed, leftPressed, downPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("1");
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            upPressed = true;
            // System.out.println("Up Arrrow-Key is pressed!");
        } else if (keyCode == KeyEvent.VK_A) {
            // System.out.println("Down Arrrow-Key is pressed!");
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_S) {
            // System.out.println("Left Arrrow-Key is pressed!");
            downPressed = true;
        } else if (keyCode == KeyEvent.VK_D) {
            // System.out.println("Right Arrrow-Key is pressed!");
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
