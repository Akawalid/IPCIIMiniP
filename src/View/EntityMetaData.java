package View;


import java.awt.*;
import java.util.HashMap;

public class EntityMetaData {
    //This class represents metadata of an entity such as, it's color, it's status(moving, growing..)
    //It is not a very elegant way to model it this way, it would be better if we derive this class to
    // ShepherdMetaData, Anim..., but the latter generates a lot of classes to handle.
    //TODO: read the comment above and think a bout the most elegant way...
    private static int colorsCounter = 0;
    //TODO: Improve after, we are limited to a small amount of colors
    private static final Color[] colorsArray = {
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
            Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK
    };

    //This one holds all the images of the game, I don't know if it is a good idea to load everything no
    //For bigger games it can damage the RAM.
    private static final HashMap<Integer, HashMap<Integer, Image>> images = null;
    /*
    //TODO: I think status should be put, in the model, we will discuss this after.
    0: Shepherd stable
    1: Shepherd in movement
    2: Shepherd smoking
    3: Animal ...
     */
    private int status;
    private final Color color;


    public EntityMetaData() {
        // Assign a rotating color from the array
        this.color = colorsArray[colorsCounter % colorsArray.length];
        colorsCounter++;
    }
    public Color applyOpacityForColor() {
        // Apply 50% opacity (alpha = 128)
        return new Color(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                128  // Alpha value (0 = fully transparent, 255 = fully opaque)
        );
    }
    public void setStatus(int s){status = s;}
    public String getStatus(){
        return switch (status) {
            case 0 -> "Shepherd is waiting";
            case 1 -> "Shepherd is moving";
            case 2 -> "Shepherd is smoking";
            default -> throw new IllegalStateException("Invalid status code: " + status);
        };
    }
    public Color getColor() {
        return color;
    }
}