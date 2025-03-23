package Model.Shepherd;

import Model.Entity;

public class Shepherd extends Entity {
    private ShepherdMovementThread thread;
    public Shepherd(String name) {
        super(name);
        thread = new ShepherdMovementThread(this);
    }
    public ShepherdMovementThread getThread(int id){
        return thread;
    }
}