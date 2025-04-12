package Model.FarmAnimals;

import Model.Resources.Wool;
import Model.Spot;

import java.util.ArrayList;

public abstract class Ovidae extends FarmAnimal{

    public Ovidae(Spot s, int price) {
        super(s, price);
        //resources of the animal
        resourceList.add(new Wool());

    }
}
