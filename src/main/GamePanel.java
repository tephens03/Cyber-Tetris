package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    // TODO: The game window will be calculated responsively later
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public final int FPS = 60;

    private Thread gameThread;
    private PlayManager pm;
    private KeyHandler kh;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setOpaque(false); // Makes the panel fully transparent
        this.setBounds(0, 0, 1280, 720);
        
        kh = new KeyHandler();
        this.addKeyListener(kh);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        pm = new PlayManager();
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // Decide the time interval between every render within 1 second (1s = 1bil ns)
        double drawInterval = 1000000000 / FPS;

        // Determine the occurent time (in the program) of the next render
        double nextDrawTime = System.nanoTime() + drawInterval;

        // The rendering process will go on while the thread is still alive
        while (gameThread.isAlive()) {

            // Perform rendering and etc.
            update();
            repaint();

            try {

                // Find the remaining time after rendering until the next render as the program
                // may have free time
                long remainingTime = (long) ((nextDrawTime - System.nanoTime()) / 10000000); // (1m ns = 1milisec)

                if (remainingTime > 0) {
                    // Sleep/stop the thread until the next render happen
                    Thread.sleep(remainingTime);
                }

            } catch (InterruptedException e) {
                System.err.println(e);
            }
            // Update next render time
            nextDrawTime += drawInterval;
        }
    }

    public void update() {
        pm.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pm.draw(g2);
    }

}
