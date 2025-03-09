package View;

import Model.Farm;
import Model.Spot;

import javax.swing.*;

public class DimensionManager {
    //This class handles coordinates convertion form the model to the view, in fact, it is possible
    // instead of creating this class, we add static mehtods to Screen class, and use them globally, but
    // it's not a very good idea because resizing doesnt concern the Screen class only.

    private int width, height;
    private JComponent component;

    public DimensionManager(int width, int height, JComponent component){
        //component is the JComponent into which we convert the model's coordinates
        // height and width are the initial dimensions of the component, if the user doesn't resize the window
            // the component will have these dimensions
        this.width = width;
        this.height = height;
        this.component = component;
    }

    //these methods convert coordinates from the model to the view
    public int scaleXOfModelToView(int x){return x * width / (Farm.WIDTH * Spot.SIZE);/*(x - LARGEUR/2) * RATIO_X*/}
    public int scaleYOfModelToView(int y){return y * height / (Farm.HEIGHT * Spot.SIZE);/*H_MAX-hauteur-H/2*/}



    //these methods handle the Frame resizing (responsivness)
    public int scaleYToReelWindowSize(int y){return y * component.getHeight() / height;}

    public int scaleXToReelWindowSize(int x){return x * component.getWidth() / width;}

}
