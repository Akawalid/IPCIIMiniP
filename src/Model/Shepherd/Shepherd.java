package Model.Shepherd;

import Model.Entity;
import Model.EntityMovementThread;
import Model.Spot;

public class Shepherd extends Entity {
    private EntityMovementThread thread;
    public Shepherd(Spot s) {
        super(s);
        thread = new EntityMovementThread(this);
    }

    @Override
    public String getSpecies() {
        return "Shepherd";
    }

    public EntityMovementThread getThread(int id){
        return thread;
    }
}