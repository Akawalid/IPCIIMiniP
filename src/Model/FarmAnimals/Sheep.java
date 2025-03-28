package Model.FarmAnimals;

import Model.Resources.Resource;
import Model.Resources.Wool;
import Model.Spot;

import java.util.ArrayList;

public class Sheep extends Ovidae{

    public Sheep(Spot s) {
        super(s);
    }

    @Override
    protected int get_price() {
        return PRICE_SHEEP;
    }

    @Override
    public String getSpecies() {
        return "Sheep";
    }
}
