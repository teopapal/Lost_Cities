package model.findings;

import javax.swing.*;

/**
 * SnakeGoddess is a class that extends the Finding class.
 * It contains the methods that are necessary for the SnakeGoddess finding.
 *
 * @version 1.0
 */
public class SnakeGoddess extends Finding{
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Creates a new SnakeGoddess finding with a description and an image
     * @param description The description of the finding
     * @param image The image of the finding
     * @param score The score of the finding
     */
    public SnakeGoddess(String description, ImageIcon image, int score) {
        super(description, image, score);
    }

}
