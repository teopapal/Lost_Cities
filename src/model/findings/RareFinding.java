package model.findings;

import javax.swing.*;

/**
 * RareFinding class represents a rare finding.
 */
public class RareFinding extends Finding{
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Creates a new rare finding with the given description and image
     * @param description The description of the finding
     * @param image The image of the finding
     * @param score The score of the finding
     */
    public RareFinding(String description, ImageIcon image, int score) {
        super(description, image, score);
    }
}
