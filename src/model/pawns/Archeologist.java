package model.pawns;

import model.Path;
import model.position.Position;

import javax.swing.*;

/**
 * Archeologist is a class that extends the Pawn class.
 * It contains the methods that are necessary for the Archeologist.
 *
 * @version 1.0
 */
public class Archeologist extends Pawn {
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Creates a new Archeologist
     * @param position The position of the Archeologist
     * @param image The image of the Archeologist
     * @param path The path of the Archeologist
     */
    public Archeologist(Position position, ImageIcon image, Path path) {
        super(position, image, path);
    }
}
