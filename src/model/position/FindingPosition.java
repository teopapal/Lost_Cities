package model.position;

import model.findings.Finding;

/**
 * FindingPosition class represents the position of the finding
 */
public class FindingPosition extends Position{
    private Finding finding;

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b> Set the finding of the position
     * @param finding The finding of the position
     */
    public void set_finding(Finding finding) {
        this.finding = finding;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The finding of the position has been returned
     * @return The finding of the position
     */
    public Finding get_finding() {
        return finding;
    }

    /**
      * <b>Constructor</b>
      * <b>Postcondition</b> Creates a new position
      * @param score The score of the position
      * @param finding The finding of the position
      */
    public FindingPosition(int score, Finding finding) {
        super(score);
        this.finding = finding;
    }
}
