package Model.FarmAnimals;

import Model.Resources.Egg;

public class Hen extends Galinacea{

    public Hen(String name){
        super(name);
        resourceList.add(new Egg());
    }
}
