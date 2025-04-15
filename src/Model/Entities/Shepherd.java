package Model.Entities;

import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.Position.Spot;

import java.util.HashSet;

public class Shepherd extends Entity {
    public static int PROTECTION_RADIUS = 2;
    private final int PRICE = 80;
    private final Farm farm;
    public Shepherd(Spot s, Farm farm) {
        super(s);
        this.farm = farm;
        setPosition(s);
    }
    public void setPosition(Spot position){
        if(farm == null) return;
        assert (position != null);
        assert (position.isTraversable());
        HashSet<Spot> chunk = new HashSet<>();
        if(this.position != null){
            //this.position is null only while creating the instance
            this.position.setIsTraversable(true);
            chunk = farm.getChunk(this.position, PROTECTION_RADIUS);
            for(Spot s: chunk){
                if(s.getProtectedArea() > 0){
                    s.unprotect();
                }
            }

            this.position.setPositionnable(null);
        }

        this.position = position;
        this.position.setPositionnable(this);
        this.position.setIsTraversable(false);
        for(Spot s: farm.getChunk(this.position, PROTECTION_RADIUS)){
            s.protect();
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