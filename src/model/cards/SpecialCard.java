package model.cards;

import javax.swing.*;

/**
 * SpecialCard is a class that extends the Card class.
 * It contains the methods that are necessary for the movement of a pawn.
 *
 * @version 1.0
 */
abstract public class SpecialCard extends Card{
    /**
     * <b>Constructor</b> Creates a new SpecialCard with the given name
     * <b>Postcondition</b> A new SpecialCard has been created with the given name
     * @param name The name of the card
     */
    public SpecialCard(String name, ImageIcon image) {
        super(name, image);
    }
}
