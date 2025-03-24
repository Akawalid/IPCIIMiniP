package Model.Shepherd;

import Model.Entity;
import Model.EntityMovementThread;

public class Shepherd extends Entity {
    private EntityMovementThread thread;
    public Shepherd(String name) {
        super(name);
        thread = new EntityMovementThread(this);
    }
    public EntityMovementThread getThread(int id){
        return thread;
    }
}