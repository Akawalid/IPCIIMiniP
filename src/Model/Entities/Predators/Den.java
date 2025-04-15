package Model.Entities.Predators;

import Model.Farm;
import Model.Position.Positionnable;
import Model.Position.Spot;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Den extends Positionnable implements Runnable {
    private static final int MAX_WOLVES = 5;
    protected Farm farm;
    protected boolean active;
    protected ArrayList<Predator> livingPredators;

    public Den(Spot s, Farm farm) {
        super(s);

        this.farm = farm;
        this.active = true;
        livingPredators = new ArrayList<>();
    }

    public void reactToAreaChange() {
        active = this.position.getProtectedArea() <= 0;
    }

    public boolean isActive(){return active;}

    /**
     * M�thode abstraite qui spawn un pr�dateur (Wolf ou Fox) depuis le den.
     */
    protected abstract void spawnPredator();
    private void updateLivingPredators() {
        for(Iterator<Predator> iterator = livingPredators.iterator(); iterator.hasNext();) {
            Predator predator = iterator.next();
            if (predator.getIsDead())  iterator.remove();
        }
    }
    @Override
    public void run() {
        while(true) {
            updateLivingPredators();
            if(livingPredators.size() < MAX_WOLVES && active) spawnPredator();
            try {
                Thread.sleep(5000); // D�lai entre chaque spawn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
