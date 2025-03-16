package Model.FarmAnimals;

import Model.Resources.Milk;
import Model.Resources.Resource;

import java.util.ArrayList;

public class Ewe extends Ovidae{

    public Ewe(String name){
        super(name);
        resourceList.add(new Milk());
    }

}
