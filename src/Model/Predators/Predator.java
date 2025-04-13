package Model.Predators;

import Model.Entity;
import Model.Farm;
import Model.Spot;
import java.util.ArrayList;
import java.util.List;

public abstract class Predator extends Entity implements Runnable {
    protected Farm farm;
    protected boolean isDead;

    public Predator(Spot s, Farm farm) {
        super(s);
        this.farm = farm;
        isDead = false;
    }
    public boolean getIsDead(){
        return isDead;
    }

    /**
     * Méthode qui vérifie les cases adjacentes et tue les proies correspondantes.
     */
    protected abstract void checkAndKillPrey();
    public void reactToAreaChange() {
        isDead = true;
    }

}
