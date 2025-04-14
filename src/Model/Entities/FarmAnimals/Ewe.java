package Model.Entities.FarmAnimals;

import Model.Resources.Milk;
import Model.Position.Spot;

public class Ewe extends Ovidae {
    private static final int PRICE = 200;

    public Ewe(Spot s){
        super(s, PRICE);
        resourceList.add(new Milk());
    }

    @Override
    public String getSpecies() {
        return "Ewe";
    }
}
