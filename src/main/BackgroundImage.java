import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImage extends JLabel {
    final String BACKGROUND_IMAGE_URL = "/Users/transtephen/Desktop/Projects/Tetris/background.gif";

    public BackgroundImage() {
        // ImageIcon bgImage = new ImageIcon(BACKGROUND_IMAGE_URL);
        // JLabel l = new JLabel(bgImage);
        // l.setPreferredSize(new Dimension(1280, 720));

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(BACKGROUND_IMAGE_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);

    }
}
