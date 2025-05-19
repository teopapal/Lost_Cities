package model.cards;

import model.pawns.Pawn;

import javax.swing.*;

/**
 * The type Card.
 */
abstract public class Card implements MovePawn{
    private final String name;
    private final ImageIcon image;
    private Pawn pawn;


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b> The pawn has been set
     * @param pawn The pawn
     */
    public void set_pawn(Pawn pawn) {
        this.pawn = pawn;
    }


    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The name of the card has been returned
     * @return The name of the card
     */
    public String get_name() {
        return name;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The image of the card has been returned
     * @return The image of the card
     */
    public ImageIcon get_card_image() {
        return image;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The number of the card has been returned
     * @return The number of the card
     */
    public int get_number() {
        return 0;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The pawn has been returned
     * @return The pawn
     */
    public Pawn get_pawn() {
        return pawn;
    }

    /**
     * <b>Constructor</b> Creates a new Card with the given name and image
     * <b>Postcondition</b> A new Card has been created with the given name and image
     *
     * @param name  The name of the card
     * @param image The image of the card
     */
    public Card(String name, ImageIcon image) {
        this.name = name;
        this.image = image;
    }
}
