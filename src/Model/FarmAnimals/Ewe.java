package Model.FarmAnimals;

import Model.Exceptions.InvalidCoordinates;
import Model.Exceptions.UnauthorizedAction;
import Model.Resources.Milk;
import Model.Resources.Resource;
import Model.Spot;

import java.util.ArrayList;

import static Model.AgeState.*;

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
