package Model.Entities.Predators;

import Model.Farm;
import Model.Position.EntityMovementThread;
import Model.Position.Spot;

public class WolfDen extends Den {

    public WolfDen(Spot s, Farm farm) {
        super(s, farm);
    }

    @Override
    protected void spawnPredator() {
        // Tente de spawn un Wolf sur une case adjacente libre
        Spot spawnSpot = farm.getAdjascentFreeUnsecureSpot(this.getPosition());
        if (spawnSpot != null) {
            Wolf wolf = new Wolf(spawnSpot, farm);
            livingPredators.add(wolf);
            farm.addEntity(wolf);
            //new Thread(wolf).start();
            new Thread(wolf).start();
        }
    }
}