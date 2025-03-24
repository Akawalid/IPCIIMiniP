package Model.FarmAnimals;

import Model.Resources.Egg;
import Model.Spot;

public class Hen extends Galinacea{

    public Hen(Spot s){
        super(s);
        resourceList.add(new Egg());
    }

    @Override
    protected int get_price() {
        return PRICE_HEN;
    }
    @Override
    public String getSpecies() {
        return "Hen";
    }
}
