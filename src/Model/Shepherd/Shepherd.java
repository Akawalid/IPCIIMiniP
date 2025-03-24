package Model.Shepherd;

import Model.Entity;
import Model.Exceptions.UnauthorizedAction;
import Model.Spot;

import static Model.AgeState.MATURE;
import static Model.AgeState.OLD;

public class Shepherd extends Entity {
    private ShepherdMovementThread thread;
    public Shepherd(Spot s) {
        super(s);
        thread = new ShepherdMovementThread(this);
    }

    @Override
    public String getSpecies() {
        return "Shepherd";
    }

    public ShepherdMovementThread getThread(int id){
        return thread;
    }
}