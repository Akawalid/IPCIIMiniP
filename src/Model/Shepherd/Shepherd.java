package Model.Shepherd;

import Model.Bank;
import Model.Entity;
import Model.EntityMovementThread;
import Model.Exceptions.UnauthorizedAction;
import Model.Spot;

public class Shepherd extends Entity {
    private EntityMovementThread thread;
    public Shepherd(Spot s) {
        super(s);
    }

    @Override
    public String getSpecies() {
        return "Shepherd";
    }

    @Override
    public int get_buying_price() {
        return Bank.get_price(this);
    }

    @Override
    public int get_selling_price() throws UnauthorizedAction {
        return 0;
    }

    public EntityMovementThread getThread(int id){
        return thread;
    }
}