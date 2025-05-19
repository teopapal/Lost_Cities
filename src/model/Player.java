package model;

import model.cards.Card;
import model.findings.Finding;
import model.pawns.Pawn;

import java.util.ArrayList;

/**
 * Player class represents a player.
 */
public class Player {
    private final String name;
    private final ArrayList<Pawn> pawns = new ArrayList<>(4);
    private ArrayList<Card> hand = new ArrayList<>(8);
    private final int score;
    private final ArrayList<Finding> findings_list = new ArrayList<>();
    private int agalmatakia = 0;


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b> adds an agalmataki to the player
     */
    public void add_agalmatakia(){
        agalmatakia++;
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b> sets the hand of the player
     * @param hand The hand of the player
     */
    public void set_hand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b> adds a finding to the player
     * @param finding The finding to be added
     */
    public void add_findings(Finding finding){
        findings_list.add(finding);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b> clears the hand of the player
     */
    public void clear_hand(){
        hand.clear();
    }


    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The hand of the player has been returned
     * @return the hand
     */
    public ArrayList<Card> get_hand() {
        return hand;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The pawns of the player have been returned
     * @return the pawns
     */
    public ArrayList<Pawn> get_pawns() {
        return pawns;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The findings of the player have been returned
     * @return the findings
     */
    public ArrayList<Finding> get_findings_list(){
        return findings_list;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The agalmatakia of the player have been returned
     * @return the agalmatakia
     */
    public int get_agalmatakia(){
        return agalmatakia;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The score of the player has been returned
     * @return the score
     */
    public int get_score() {
        return score;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The name of the player has been returned
     * @return the name
     */
    public String get_name(){
        return name;
    }


    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> A new player has been created
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
}
