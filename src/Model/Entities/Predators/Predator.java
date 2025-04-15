package Model.Entities.Predators;

import Model.Entities.Entity;
import Model.Entities.FarmAnimals.FarmAnimal;
import Model.Farm;
import Model.Position.Spot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Predator extends Entity implements Runnable {
    protected Farm farm;
    protected boolean pause = false;

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
     * Méthode qui vérifie les cases adjacentes et tue les proies correspondantes.
     */

    public void reactToAreaChange() {
        kill();
    }

}
