package Model.FarmAnimals;

import Model.Resources.Resource;
import Model.Resources.Wool;
import Model.Spot;

import java.util.ArrayList;

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
