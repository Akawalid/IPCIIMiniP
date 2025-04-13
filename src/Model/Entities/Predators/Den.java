package Model.Entities.Predators;

import Model.Entities.Entity;
import Model.Farm;
import Model.Position.Positionnable;
import Model.Position.Spot;

import java.util.Iterator;

public abstract class Den extends Positionnable implements Runnable {
    private static final int MAX_WOLVES = 5;
    protected Farm farm;
    protected boolean active;

    public Den(Spot s, Farm farm) {
        super(s);

        this.farm = farm;
        this.active = true;
    }

    public void reactToAreaChange() {
        active = !this.position.isProtectedArea();
    }

    public boolean isActive(){return active;}

    /**
     * M�thode abstraite qui spawn un pr�dateur (Wolf ou Fox) depuis le den.
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
            // Si un shepherd est � c�t�, le den dispara�t de la simulation
            /*if (isShepherdNearby()) {
                active = false;
                farm.removeEntity(this);
                break;
            }*/
            // Sinon, le den spawn un pr�dateur
            if(numberOfLivingWolves() < MAX_WOLVES) spawnPredator();
            try {
                Thread.sleep(5000); // D�lai entre chaque spawn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
