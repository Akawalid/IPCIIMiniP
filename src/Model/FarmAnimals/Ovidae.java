package Model.FarmAnimals;

import Model.Resources.Wool;

import java.util.ArrayList;

public abstract class Ovidae extends FarmAnimal{

    public Ovidae(String name) {
        super(name);
        //resources of the animal
        resourceList.add(new Wool());

    }
}
