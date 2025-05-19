package model.findings;

import model.position.Position;

import javax.swing.*;


/**
 * Findings is the class that represents the findings of the game.
 *
 * @version 1.0
 * @since 1.0
 */
public interface findings {
    /**
     * <b>Accessor:</b> returns the description of the finding
     * <b>Postcondition:</b> the description of the finding is returned
     * @return the description
     */
    String get_description();

    /**
     * <b>Accessor:</b> returns the image of the finding
     * <b>Postcondition:</b> the image of the finding is returned
     * @return the image
     */
    ImageIcon get_image();

    /**
     * <b>Accessor:</b> returns the position of the finding
     * <b>Postcondition:</b> the position of the finding is returned
     * @return the position
     */
    Position get_position();
}
