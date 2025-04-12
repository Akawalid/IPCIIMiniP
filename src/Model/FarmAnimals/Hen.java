package Model.FarmAnimals;

import Model.Resources.Egg;
import Model.Spot;

public class Hen extends Galinacea{
    private static final int PRICE = 40;
    public Hen(Spot s){
        super(s, PRICE);
        resourceList.add(new Egg());
    }

    @Override
    public String getSpecies() {
        return "Hen";
    }
    public int get_buying_price() {
        return PRICE;
    }
}
