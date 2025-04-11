package Model.Predators;

import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.Spot;

public class WolfDen extends Den {

    public WolfDen(Spot s, Farm farm) {
        super(s, farm);
    }

    @Override
    protected void spawnPredator() {
        // Tente de spawn un Wolf sur une case adjacente libre
        Spot spawnSpot = farm.getAdjacentFreeSpot(this.getPosition());
        if (spawnSpot != null) {
            Wolf wolf = new Wolf(spawnSpot, farm);
            farm.addEntity(wolf);
            new Thread(wolf).start();
        }
    }


    @Override
    public String getSpecies() {
        return null;
    }

    @Override
    public int get_buying_price() {
        return 0;
    }

    @Override
    public int get_selling_price() throws UnauthorizedAction {
        return 0;
    }
}
