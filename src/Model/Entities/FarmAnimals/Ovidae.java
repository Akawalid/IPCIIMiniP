package Model.Entities.FarmAnimals;

import Model.Resources.Wool;
import Model.Position.Spot;

public abstract class Ovidae extends FarmAnimal{

    public Ovidae(Spot s, int price) {
        super(s, price);
        //resources of the animal
        resourceList.add(new Wool());

    }
}
