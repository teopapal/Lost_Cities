package model.cards;

/**
 * MovePawn is an interface that is implemented by the cards that move a pawn.
 * It contains the methods that are necessary for the movement of a pawn.
 *
 * @version 1.0
 * @since 1.0
 */
public interface MovePawn {

    /**
     * <b>Accessor</b>: Returns the name of the card
     * <b>Postcondition</b>: The name of the card has been returned
     */
    String get_name();

    /**
     * <b>Accessor</b>: Returns the number of the card
     * <b>Postcondition</b>: The number of the card has been returned
     */
    int get_number();
}
