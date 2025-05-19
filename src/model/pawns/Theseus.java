package model.pawns;

import model.Path;
import model.position.Position;

import javax.swing.*;

/**
 * Theseus is a class that extends the Pawn class.
 * It contains the methods that are necessary for the Theseus pawn.
 *
 * @version 1.0
 */
public class Theseus extends Pawn {
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Creates a new Theseus pawn with a position, an image and a path
     * @param position The position of the pawn
     * @param image The image of the pawn
     * @param path The path of the pawn
     */
    public Theseus(Position position, ImageIcon image, Path path) {
        super(position, image, path);
    }
}
