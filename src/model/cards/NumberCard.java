package model.cards;

import javax.swing.*;

/**
 * NumberCard class represents a number card.
 */
public class NumberCard extends Card{
    private final int number;
        /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The number of the card has been returned
     * @return The number of the card
     */
    public int get_number() {
        return number;
    }

    /**
     * <b>Constructor</b> Creates a new NumberCard with the given number
     * <b>Postcondition</b> A new NumberCard has been created with the given number
     *
     * @param number The number of the card
     * @param number The number of the card
     * @param image The image of the card
     */
    public NumberCard(String name, int number, ImageIcon image) {
        super(name, image);
        this.number = number;
    }
}
