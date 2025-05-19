package model.findings;

import javax.swing.*;

/**
 * Fresco class represents a fresco.
 */
public class Fresco extends Finding{
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Creates a new fresco with the given description and image
     * @param description The description of the fresco
     * @param image The image of the fresco
     * @param score The score of the fresco
     */
    public Fresco(String description, ImageIcon image, int score) {
        super(description, image, score);
    }
}
