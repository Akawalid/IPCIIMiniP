package Model.Entities.FarmAnimals;

import Model.Position.Spot;

public class Sheep extends Ovidae{
    private static final int PRICE = 100;
    public Sheep(Spot s) {
        super(s, PRICE);
    }

    @Override
    public String getSpecies() {
        return "Sheep";
    }
}
