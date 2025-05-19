package model.utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * <b>Accessor</b>
 * <b>Postcondition</b> returns the selected image from the folder
 */
public class Image_reader {
    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> returns the selected image from the folder
     * @param name The name of the image
     * @param folder The folder of the image
     */
    public static ImageIcon get_Image(String name, String folder) {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(Image_reader.class.getResource("/resources/images/" + folder + "/" + name + ".jpg")))
                    .getScaledInstance(100, 80, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return null;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> returns the selected image
     * @param name The name of the image
     */
    public static ImageIcon get_Image(String name) {
        try {
            if (name.equals("background")) {
                return new ImageIcon(ImageIO.read(Objects.requireNonNull(Image_reader.class.getResource("/resources/images/background.jpg")))
                        .getScaledInstance(1000, 800, Image.SCALE_SMOOTH));
            }
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(Image_reader.class.getResource("/resources/images/" + name + ".jpg")))
                    .getScaledInstance(100, 80, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return null;
    }
}
