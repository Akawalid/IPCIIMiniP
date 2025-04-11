package Model.FarmAnimals;

import Model.Exceptions.InvalidCoordinates;
import Model.Resources.Milk;
import Model.Resources.Resource;
import Model.Spot;

import java.util.ArrayList;

public class Ewe extends Ovidae {

    public Ewe(Spot s){
        super(s);
        resourceList.add(new Milk());
    }

    @Override
    public String getSpecies() {
        return "Ewe";
    }
}
