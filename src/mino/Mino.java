package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import main.KeyHandler;
import main.PlayManager;

abstract public class Mino {
    public Block[] blocks;
    public Block[] tempBlocks;
    public int autoDropCounter;
    public int direction;

    public Mino(Color c) {
        autoDropCounter = 0;
        direction = 1;
        blocks = new Block[4];
        tempBlocks = new Block[4];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(c);
            tempBlocks[i] = new Block(c);
        }
    }

    public void setXY(int x, int y) {
    }

    public void updateXY(int direction) {
        this.direction = direction;
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].x = tempBlocks[i].x;
            blocks[i].y = tempBlocks[i].y;
        }

    }

    public void update() {
        // contain the number of px the mino will shift (if any) to left or right
        int distanceToShift = 0;

        if (KeyHandler.upPressed) {
            System.out.println("Up Arrrow-Key is pressed!");
            rotateMino();
            KeyHandler.upPressed = false;

        } else if (KeyHandler.leftPressed) {
            System.out.println("Left Arrrow-Key is pressed!");
            KeyHandler.leftPressed = false;
            distanceToShift = -Block.SIZE; // will go "back" Block.SIZE px therefore a negative value
        } else if (KeyHandler.downPressed) {
            System.out.println("Down Arrrow-Key is pressed!");
            KeyHandler.downPressed = false;
            autoDropCounter = PlayManager.DROP_INTERVAL - 1;
        } else if (KeyHandler.rightPressed) {
            System.out.println("Right Arrrow-Key is pressed!");
            KeyHandler.rightPressed = false; // will go forward Block.SIZEpx therefore positive value
            distanceToShift = Block.SIZE;
        }

        // A bit repetitive and hard to read
        // ++autoDropCounter;
        // for (Block block : blocks) {
        // if (distanceToShift == 0 && autoDropCounter % PlayManager.DROP_INTERVAL != 0)
        // {
        // break;
        // }
        // block.x += distanceToShift;
        // if (autoDropCounter % PlayManager.DROP_INTERVAL == 0) {
        // block.y += Block.SIZE;
        // }
        // }

        // Iteration may happen more than once in return for readiblilty
        if (distanceToShift != 0) {
            for (Block block : blocks) {
                block.x += distanceToShift;
            }
        }
        // if (++autoDropCounter == PlayManager.DROP_INTERVAL) {
        // for (Block block : blocks) {
        // block.y += Block.SIZE;
        // }
        // autoDropCounter = 0;
        // }

    }

    public void draw(Graphics2D g2) {
        for (Block block : blocks) {
            g2.setColor(Color.WHITE);
            g2.fillRect(block.x, block.y, Block.SIZE, Block.SIZE);
            g2.setColor(blocks[0].color);
            g2.fillRect(block.x + Block.MARGIN, block.y + Block.MARGIN, Block.SIZE - (2 * Block.MARGIN),
                    Block.SIZE - (2 * Block.MARGIN));
        }
    }

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

    public void updateDirection1() {
    }

    public void updateDirection2() {
    }

    public void updateDirection3() {
    }

    public void updateDirection4() {
    }

}
