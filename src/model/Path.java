package model;

import model.position.Position;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Path class represents a path.
 */
public class Path {
    private final String name;
    private final Position[] positions;
    private final Map<Integer, JLabel> labels = new HashMap<>();
    /**
     * <b>Transformer</b>
     * <b>Postcondition:</b> sets the position of the path
     * @param position  the new position
     * @param index the index of the position
     */
    public void set_position(int index, Position position) {
        if (index >= 0 && index < positions.length) {
            positions[index] = position;
        } else {
            System.out.println("Invalid position index!");
        }
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition:</b> sets the label of the path
     * @param index  the index of the label
     * @param label the new label
     */
    public void set_label(int index, JLabel label) {
        labels.put(index, label);
    }


    /**
     * <b>Accessor</b>
     * <b>Postcondition:</b> returns the position of the path
     * @param index the index of the position
     * @return position
     */
    public Position get_position(int index) {
        if (index >= 0 && index < positions.length) {
            return positions[index];
        } else {
            System.out.println("Invalid position index!");
            return null;
        }
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition:</b> returns the index of the position
     * @param position the position
     * @return the index of the position
     */
    public int get_index_of_position(Position position) {
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == position) {
                return i;
            }
        }
        return -1;
    }


    /**
     * <b>Accessor</b>
     * <b>Postcondition:</b> returns the label of the path
     * @param index the index of the label
     * @return label
     */
    public JLabel get_label(int index) {
        return labels.get(index);
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition:</b> returns the length of the path
     * @return length
     */
    public int get_length() {
        return positions.length;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition:</b> returns the name of the path
     * @return name
     */
    public String get_name() {
        return name;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition:</b> returns the original icon of the path
     * @param index the index of the label
     * @return icon
     */
    public ImageIcon get_original_icon(int index) {
        return (ImageIcon) labels.get(index).getIcon();
    }

    /**
     * <b>Constructor</b> Creates a new path
     * <b>Postcondition:</b> A new path is created
     * @param name The name of the path
     * @param position_length The length of the path
     */
    public Path(String name, int position_length) {
        this.name = name;
        this.positions = new Position[position_length];
    }
}
