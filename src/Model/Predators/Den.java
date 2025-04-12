package Model.Predators;

import Model.Entity;
import Model.Farm;
import Model.Spot;

import java.util.Iterator;

public abstract class Den implements Runnable {
    private static final int MAX_WOLVES = 5;
    protected Farm farm;
    private Spot position;
    protected boolean active;

    public Den(Spot s, Farm farm) {
        this.position = s;
        this.position.setIsTraversable(false);
        this.position.setPositionnable(null);
        this.farm = farm;
        this.active = true;
    }

    public Spot getPosition() {
        return position;
    }

    /**
     * Méthode abstraite qui spawn un prédateur (Wolf ou Fox) depuis le den.
     */
    protected abstract void spawnPredator();
    private int numberOfLivingWolves() {
        int count = 0;
        for (Iterator<Entity> it = farm.getEntities(); it.hasNext(); ) {
            Entity entity = it.next();
            if (entity instanceof Wolf) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void run() {
        while (active) {
            // Si un shepherd est à côté, le den disparaît de la simulation
            /*if (isShepherdNearby()) {
                active = false;
                farm.removeEntity(this);
                break;
            }*/
            // Sinon, le den spawn un prédateur
            if(numberOfLivingWolves() < MAX_WOLVES) spawnPredator();
            try {
                Thread.sleep(5000); // Délai entre chaque spawn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
