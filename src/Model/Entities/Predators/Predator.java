package Model.Entities.Predators;

import Model.Entities.Entity;
import Model.Entities.FarmAnimals.FarmAnimal;
import Model.Farm;
import Model.Position.Spot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract class representing a Predator in the game.
 * This class extends Entity and implements Runnable to allow for concurrent execution.
 */
public abstract class Predator extends Entity implements Runnable {
    protected Farm farm;
    protected boolean pause = false;

    /**
     * Constructor for Predator.
     * Initializes the Predator with its starting position and a reference to the farm.
     *
     * @param s the starting Spot (location) of the predator.
     * @param farm the Farm object representing the game environment.
     */

    public Predator(Spot s, Farm farm) {
        super(s);
        this.farm = farm;
    }

    public void pauseThread(){
        pause = true;
    }

    public void resumeThread(){
        pause = false;
    }

    /**
     * Méthode stopPredator() qui arrête le prédateur.
     * Elle marque le prédateur comme "mort" en appelant kill() (qui met le flag isDead à true
     * et libère la position), ce qui provoque l'arrêt de la boucle dans run().
     */
    /*public void stopPredator() {
        if (!getIsDead()) {  // Si le prédateur n'est pas déjà stoppé
            kill();  // kill() est défini dans Entity et met isDead à true
            System.out.println(getSpecies() + " (" + getId() + ") a été stoppé.");
        }
    }*/

    public void reactToAreaChange() {
        kill();
    }


    /**
     * Abstract method to be implemented by subclasses.
     * This method defines the behavior of the predator when it is running.
     */
    protected List<Spot> getAdjacentSpots(Spot s) {
        List<Spot> neighbors = new ArrayList<>();
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        for (int[] d : directions) {
            int newRow = s.getRow() + d[0];
            int newCol = s.getCol() + d[1];
            // V�rifier que les indices sont dans les limites du terrain
            if (newRow >= 0 && newRow < Farm.HEIGHT && newCol >= 0 && newCol < Farm.WIDTH) {
                neighbors.add(farm.getSpot(newRow, newCol));
            }
        }
        return neighbors;
    }
}
