package Model.Shepherd;

import Model.Entity;
import Model.Exceptions.InvalidCoordinates;
import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.Spot;

import java.util.HashSet;

public class Shepherd extends Entity {
    public static int PROTECTION_RADIUS = 3;
    private int PRICE = 80;
    private Farm farm;
    public Shepherd(Spot s, Farm farm) {
        super(s);
        this.farm = farm;
    }
    public void setPosition(Spot position){
        assert (position != null);
        assert (position.isTraversable());
        HashSet<Spot> chunk = new HashSet<>();
        if(this.position != null){
            //this.position is null only while creating the instance
            this.position.setIsTraversable(true);
            if (farm != null)
            chunk = farm.getChunk(this.position, PROTECTION_RADIUS);


            this.position.setPositionnable(null);
        }

        this.position = position;
        this.position.setPositionnable(this);
        this.position.setIsTraversable(false);
        if(farm != null)
        for(Spot s: farm.getChunk(this.position, PROTECTION_RADIUS)){
            if(chunk.contains(s)){
                chunk.remove(s);
                s.setIsProtectedArea(true);
            }
        }
        for(Spot s: chunk){
            s.setIsProtectedArea(false);
        }
    }

    @Override
    public String getSpecies() {
        return "Shepherd";
    }

    @Override
    public int get_buying_price() {
        return PRICE;
    }

    @Override
    public int get_selling_price() throws UnauthorizedAction {
        return 0;
    }
}