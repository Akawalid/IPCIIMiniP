package Model.FarmAnimals;

import java.util.Iterator;

import Model.AgeState;
import Model.Farm;
import Model.Entity;

/**
 * Simulation thread that updates the model's state.
 * This thread iterates over all the entities in the farm and calls updateAge()
 * on objects of type FarmAnimal at regular intervals.
 */
public class SimulationUpdateAgeThread extends Thread {
    // Update delay in milliseconds (5 seconds)
    public static final int UPDATE_DELAY = 5000;
    private Farm farm;

    /**
     * Constructor that initializes the thread with the farm.
     * @param farm the farm containing the entities to be updated.
     */
    public SimulationUpdateAgeThread(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void run() {
        while (true) {
            // Iterate over all entities and update the age for each farm animal
            Iterator<Entity> entity = farm.getEntities();
            while (entity.hasNext()) {
                Entity e = entity.next();
                // Check if the entity is a FarmAnimal before calling updateAge()
                if (e instanceof FarmAnimal) {
                    ((FarmAnimal) e).updateAge();
                    if (((FarmAnimal) e).getState() == AgeState.DEAD) {
                        e.getPosition().setIsTraversable(true);  // Free up the cell
                        entity.remove(); // If the animal is dead, remove it from the farm
                    }
                }
            }
            try {
                Thread.sleep(UPDATE_DELAY); // Pause 5s before the next update
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}