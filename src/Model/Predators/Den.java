package Model.Predators;

import Model.Entity;
import Model.Farm;
import Model.Shepherd.Shepherd;
import Model.Spot;
import java.util.ArrayList;
import java.util.List;

public abstract class Den extends Entity implements Runnable {
    protected Farm farm;
    protected boolean active;

    public Den(Spot s, Farm farm) {
        super(s);
        this.farm = farm;
        this.active = true;
    }

    /**
     * M�thode abstraite qui spawn un pr�dateur (Wolf ou Fox) depuis le den.
     */
    protected abstract void spawnPredator();

    /**
     * Retourne la liste des cases adjacentes (haut, bas, gauche, droite) � la case donn�e.
     */
    /*protected List<Spot> getAdjacentSpots(Spot s) {
        List<Spot> neighbors = new ArrayList<>();
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        for (int[] d : directions) {
            int newRow = s.getRow() + d[0];
            int newCol = s.getCol() + d[1];
            if (newRow >= 0 && newRow < Farm.HEIGHT && newCol >= 0 && newCol < Farm.WIDTH) {
                neighbors.add(farm.getSpot(newRow, newCol));
            }
        }
        return neighbors;
    }*/

    /**
     * V�rifie si un Shepherd se trouve sur une case adjacente.
     */
    /*protected boolean isShepherdNearby() {
        for (Spot neighbor : farm.getAdjacentSpots(this.getPosition()) {
            if (farm.getEntityInSpot(neighbor.getRow(), neighbor.getCol()) instanceof Shepherd) {
                return true;
            }
        }
        return false;
    }*/

    @Override
    public void run() {
        while (active) {
            // Si un shepherd est � c�t�, le den dispara�t de la simulation
            /*if (isShepherdNearby()) {
                active = false;
                farm.removeEntity(this);
                break;
            }*/
            // Sinon, le den spawn un pr�dateur
            spawnPredator();
            try {
                Thread.sleep(5000); // D�lai entre chaque spawn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
