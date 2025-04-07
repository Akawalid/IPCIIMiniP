package Model.Predators;

import Model.Entity;
import Model.Farm;
import Model.Spot;
import java.util.ArrayList;
import java.util.List;

public abstract class Predator extends Entity implements Runnable {
    protected Farm farm;

    public Predator(Spot s, Farm farm) {
        super(s);
        this.farm = farm;
    }

    /**
     * Méthode qui vérifie les cases adjacentes et tue les proies correspondantes.
     */
    protected abstract void checkAndKillPrey();


}
