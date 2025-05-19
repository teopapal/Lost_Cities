package model.position;

/**
 * Position class represents a position.
 */
abstract public class Position {
    private final int score;

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The score of the position has been returned
     * @return The score of the position
     */
    public int get_score() {
        return score;
    }


   /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Creates a new position
     * @param score The score of the position
     */
    public Position(int score) {
        this.score = score;
    }
}
