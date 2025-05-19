package model.findings;

import model.position.Position;

import javax.swing.*;

/**
 * Finding class represents a finding.
 */
abstract public class Finding implements findings{
    private final String description;
    private final ImageIcon image;
    private Position position;
    private final int score;


    /**
     * <b>Transformer</b> The position of the finding has been set
     * <b>Postcondition</b> The position of the finding has been set
     * @param position The position of the finding
     */
    public void set_position(Position position) {
        this.position = position;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The description of the finding has been returned
     * @return The description of the finding
     */
    @Override
    public String get_description() {
        return description;
    }


    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The image of the finding has been returned
     * @return The image of the finding
     */
    @Override
    public ImageIcon get_image() {
        return image;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The position of the finding has been returned
     * @return The position of the finding
     */
    @Override
    public Position get_position() {
        return position;
    }


    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The score of the finding has been returned
     * @return The score of the finding
     */
    public int get_score() {
        return score;
    }


    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Creates a new finding with the given description and image
     * @param description The description of the finding
     * @param image The image of the finding
     */
    public Finding(String description, ImageIcon image, int score) {
        this.description = description;
        this.image = image;
        this.score = score;
    }
}
