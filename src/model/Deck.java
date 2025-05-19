package model;

import model.cards.Ariadne;
import model.cards.Card;
import model.cards.Minotaur;
import model.cards.NumberCard;
import model.utilities.Image_reader;

import javax.swing.*;
import java.util.ArrayList;

import static java.util.Collections.shuffle;

/**
 * Deck class represents a deck of cards.
 */
public class Deck extends JButton{
    private final ArrayList<Card> deck = new ArrayList<>(50);
    private ArrayList<Card> cards;

    /**
     * <b>Transformer</b> Initializes the deck with all the cards
     * <b>Postcondition:</b> The deck is initialized with all the cards
     */
    private void initialize_deck() {
        for (int i = 0; i < 2; i++) {
            deck.add(new NumberCard("knossos1", 1, Image_reader.get_Image("knossos1", "cards")));
            deck.add(new NumberCard("knossos2", 2, Image_reader.get_Image("knossos2", "cards")));
            deck.add(new NumberCard("knossos3", 3, Image_reader.get_Image("knossos3", "cards")));
            deck.add(new NumberCard("knossos4", 4, Image_reader.get_Image("knossos4", "cards")));
            deck.add(new NumberCard("knossos5", 5, Image_reader.get_Image("knossos5", "cards")));
            deck.add(new NumberCard("knossos6", 6, Image_reader.get_Image("knossos6", "cards")));
            deck.add(new NumberCard("knossos7", 7, Image_reader.get_Image("knossos7", "cards")));
            deck.add(new NumberCard("knossos8", 8, Image_reader.get_Image("knossos8", "cards")));
            deck.add(new NumberCard("knossos9", 9, Image_reader.get_Image("knossos9", "cards")));
            deck.add(new NumberCard("knossos10", 10, Image_reader.get_Image("knossos10", "cards")));
            deck.add(new NumberCard("malia1", 1, Image_reader.get_Image("malia1", "cards")));
            deck.add(new NumberCard("malia2", 2, Image_reader.get_Image("malia2", "cards")));
            deck.add(new NumberCard("malia3", 3, Image_reader.get_Image("malia3", "cards")));
            deck.add(new NumberCard("malia4", 4, Image_reader.get_Image("malia4", "cards")));
            deck.add(new NumberCard("malia5", 5, Image_reader.get_Image("malia5", "cards")));
            deck.add(new NumberCard("malia6", 6, Image_reader.get_Image("malia6", "cards")));
            deck.add(new NumberCard("malia7", 7, Image_reader.get_Image("malia7", "cards")));
            deck.add(new NumberCard("malia8", 8, Image_reader.get_Image("malia8", "cards")));
            deck.add(new NumberCard("malia9", 9, Image_reader.get_Image("malia9", "cards")));
            deck.add(new NumberCard("malia10", 10, Image_reader.get_Image("malia10", "cards")));
            deck.add(new NumberCard("phaistos1", 1, Image_reader.get_Image("phaistos1", "cards")));
            deck.add(new NumberCard("phaistos2", 2, Image_reader.get_Image("phaistos2", "cards")));
            deck.add(new NumberCard("phaistos3", 3, Image_reader.get_Image("phaistos3", "cards")));
            deck.add(new NumberCard("phaistos4", 4, Image_reader.get_Image("phaistos4", "cards")));
            deck.add(new NumberCard("phaistos5", 5, Image_reader.get_Image("phaistos5", "cards")));
            deck.add(new NumberCard("phaistos6", 6, Image_reader.get_Image("phaistos6", "cards")));
            deck.add(new NumberCard("phaistos7", 7, Image_reader.get_Image("phaistos7", "cards")));
            deck.add(new NumberCard("phaistos8", 8, Image_reader.get_Image("phaistos8", "cards")));
            deck.add(new NumberCard("phaistos9", 9, Image_reader.get_Image("phaistos9", "cards")));
            deck.add(new NumberCard("phaistos10", 10, Image_reader.get_Image("phaistos10", "cards")));
            deck.add(new NumberCard("zakros1", 1, Image_reader.get_Image("zakros1", "cards")));
            deck.add(new NumberCard("zakros2", 2, Image_reader.get_Image("zakros2", "cards")));
            deck.add(new NumberCard("zakros3", 3, Image_reader.get_Image("zakros3", "cards")));
            deck.add(new NumberCard("zakros4", 4, Image_reader.get_Image("zakros4", "cards")));
            deck.add(new NumberCard("zakros5", 5, Image_reader.get_Image("zakros5", "cards")));
            deck.add(new NumberCard("zakros6", 6, Image_reader.get_Image("zakros6", "cards")));
            deck.add(new NumberCard("zakros7", 7, Image_reader.get_Image("zakros7", "cards")));
            deck.add(new NumberCard("zakros8", 8, Image_reader.get_Image("zakros8", "cards")));
            deck.add(new NumberCard("zakros9", 9, Image_reader.get_Image("zakros9", "cards")));
            deck.add(new NumberCard("zakros10", 10, Image_reader.get_Image("zakros10", "cards")));
            deck.add(new Minotaur("knossosmin", Image_reader.get_Image("KnossosMin", "cards")));
            deck.add(new Minotaur("maliamin", Image_reader.get_Image("maliaMin", "cards")));
            deck.add(new Minotaur("phaistosmin", Image_reader.get_Image("phaistosMin", "cards")));
            deck.add(new Minotaur("zakrosmin", Image_reader.get_Image("zakrosMin", "cards")));
        }

        for (int i = 0; i < 3; i++) {
            deck.add(new Ariadne("knossosari", Image_reader.get_Image("knossosAri", "cards")));
            deck.add(new Ariadne("maliaari", Image_reader.get_Image("maliaAri", "cards")));
            deck.add(new Ariadne("phaistosari", Image_reader.get_Image("phaistosAri", "cards")));
            deck.add(new Ariadne("zakrosari", Image_reader.get_Image("zakrosAri", "cards")));
        }
    }

    /**
     * <b>Transformer</b> sets the deck
     * <b>Postcondition:</b> The deck is set
     * @param cards The deck to be set
     */
    public void set_cards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition:</b> The deck is returned
     * @return The deck
     */
    public ArrayList<Card> get_deck(){
        return deck;
    }


    /**
     * <b>Accessor</b> Draws a card from the deck
     * <b>Postcondition:</b> The card is drawn from the deck
     * @return The card that is drawn
     */
    public Card draw_card() {
        if (!cards.isEmpty()) {
            return cards.removeFirst();
        }
        return null;
    }

    /**
     * <b>Accessor</b> Returns the size of the deck
     * <b>Postcondition:</b> The size of the deck is returned
     * @return The size of the deck
     */
    public int get_size() {
        return cards.size();
    }

    /**
     * <b>Constructor</b> Creates a new deck
     * <b>Postcondition:</b> A new deck is created
     */
    public Deck(){
        initialize_deck();
        shuffle(deck);
        ImageIcon back = Image_reader.get_Image("backCard", "cards");
        setIcon(back);
    }
}
