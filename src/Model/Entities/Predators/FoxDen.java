package Model.Entities.Predators;

import Model.Farm;
import Model.Position.EntityMovementThread;
import Model.Position.Spot;


/**
 * The FoxDen class represents a den where foxes can spawn.
 * It extends the Den class and implements the spawnPredator() method to create foxes.
 */
public class FoxDen extends Den {

    /**
     * Constructor for the FoxDen.
     * @param s the Spot where the den is located.
     * @param farm the Farm object that contains the game state.
     */

    public FoxDen(Spot s, Farm farm) {
        super(s, farm);
    }


    /**
     * Implementation of the abstract method spawnPredator().
     * This method attempts to spawn a Fox on an adjacent free and unprotected spot.
     */
    @Override
    protected void spawnPredator() {
        // Tente de spawn un Wolf sur une case adjacente libre
        Spot spawnSpot = farm.getAdjascentFreeUnsecureSpot(this.getPosition());
        if (spawnSpot != null) {
            Fox fox = new Fox(spawnSpot, farm);
            livingPredators.add(fox);
            farm.addEntity(fox);
            new Thread(fox).start();
        }
    }



}
