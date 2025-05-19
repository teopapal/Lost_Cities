package model.pawns;

import model.Path;
import model.Player;
import model.position.Position;

import javax.swing.*;

/**
 * Pawn class represents a pawn.
 */
abstract public class Pawn {
    private Position position;
    private final ImageIcon image;
    private final JLabel label;
    private final Path path;
    private Position previous_position;


    /**
     * <b>Transformer:</b> sets the position of the pawn
     * <b>Postcondition:</b> the position of the pawn is set
     * @param position the position of the pawn
     */
    public void move_pawn(Position position) {
        this.position = position;
    }

    /**
     * <b>Transformer:</b> sets the owner of the pawn
     * <b>Postcondition:</b> the owner of the pawn is set
     * @param owner the owner of the pawn
     */
    public void set_owner(Player owner) {
        label.setName(owner.get_name());
    }

    /**
     * <b>Transformer:</b> sets the position of the pawn
     * <b>Postcondition:</b> the position of the pawn is set
     * @param position the owner of the pawn
     */
    public void set_pawn_position(Position position) {
        this.position = position;
    }

    /**
     * <b>Accessor:</b> returns the position of the pawn
     * <b>Postcondition:</b> the position of the pawn is returned
     * @return the position
     */

    public Position get_pawn_position() {
        return position;
    }

    /**
     * <b>Accessor:</b> returns the image of the pawn
     * <b>Postcondition:</b> the image of the pawn is returned
     * @return the image
     */
    public ImageIcon get_pawn_image() {
        return image;
    }


    /**
     * <b>Accessor:</b> returns the path of the pawn
     * <b>Postcondition:</b> the path of the pawn is returned
     * @return the path
     */
    public Path get_path() {
        return path;
    }

    /**
     * <b>Accessor:</b> returns the previous position of the pawn
     * <b>Postcondition:</b> the previous position of the pawn is returned
     * @return the previous position
     */
    public Position get_previous_position() {
        return previous_position;
    }

    /**
     * <b>Accessor:</b> returns if the pawn is at the end of the path
     * <b>Postcondition:</b> if the pawn is at the end of the path, true is returned, otherwise false
     * @return if the pawn is at the end of the path
     */
    public boolean isAtEnd() {
        if (path == null || get_pawn_position() == null) {
            return false;
        }

        int positionIndex = path.get_index_of_position(get_pawn_position());
        return positionIndex == path.get_length() - 1;
    }

    /**
     * <b>Constructor:</b> creates a new pawn
     * <b>Postcondition:</b> a new pawn is created
     * @param position the position of the pawn
     */
    public Pawn(Position position, ImageIcon image, Path path) {
        this.position = position;
        this.image = image;
        this.path = path;
        label = new JLabel(image);
    }

}
