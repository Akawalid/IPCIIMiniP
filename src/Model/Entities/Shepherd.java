package Model.Entities;

import Model.Entities.Entity;
import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.Position.Spot;

import java.util.HashSet;

public class Shepherd extends Entity {
    public static int PROTECTION_RADIUS = 3;
    private final int PRICE = 80;
    private final Farm farm;
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
            System.out.println("aaaaaaaaaaaa " + s.getRow() + "; " + s.getCol());
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