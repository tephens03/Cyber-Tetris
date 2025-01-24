package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A JLabel subclass for rendering a background image in the application.
 * This class ensures the background image is displayed and scaled to fit
 * the specified dimensions.
 */
final class BackgroundImage extends JLabel {

    // Path to the background image file.
    private final String BACKGROUND_IMAGE_URL = "../asset/background.gif";

    /**
     * Constructor to initialize the background image and set its dimensions.
     *
     * @param width  The width of the background image.
     * @param height The height of the background image.
     */
    protected BackgroundImage(int width, int height) {
        // Load the background image from the specified path.
        ImageIcon bgImage = new ImageIcon(BACKGROUND_IMAGE_URL);

        // Set the icon to this JLabel.
        this.setIcon(bgImage);

        // Set the position and size of the background image.
        // The image is positioned at the top-left corner (0, 0) and scaled
        // to the specified width and height.
        this.setBounds(0, 0, width, height);
    }
}
