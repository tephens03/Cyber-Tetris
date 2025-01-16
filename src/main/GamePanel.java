package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    protected static final int WIDTH = 1280;
    protected static final int HEIGHT = 720;
    final int FPS = 60;
    Thread gameThread;
    PlayManager pm;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setOpaque(false); // Makes the panel fully transparent
        this.setBounds(0, 0, 1280, 720);
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
            // System.err.println("painted");

            try {
                // Find the remaining time after rendering, as the program may have free time
                // until the next render. (1m ns = 1 milisec)

                long remainingTime = (long) ((nextDrawTime - System.nanoTime()) / 10000000);

                if (remainingTime > 0) {
                    // Sleep/stop the thread until the next render happen
                    Thread.sleep(remainingTime);
                    // move_length += 1;
                }

            } catch (InterruptedException e) {
                System.err.println(e);

            }

            // Update next render time
            nextDrawTime += drawInterval;

        }
    }

    private void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pm.draw(g2);
    }

}
