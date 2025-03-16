package Model;

import java.awt.*;
import java.awt.List;
import java.util.*;

public class Farm {
    //Farm is out model, it contains the creatures and the land
    //each creature, has a reference to the spot on which it   is standing.

    public static final int WIDTH = 20, HEIGHT = 15;//spots
    private HashSet<Entity> creatures;
    private ArrayList<Spot> spots;
    private Bank bank;

    public Farm(){
        creatures = new HashSet<>();
        spots = new ArrayList<>();

        bank = new Bank();

        initLand();
        Shepherd s = new Shepherd("John");
        s.setPosition(spots.get(3));
        creatures.add(s);
    }

    private void initLand(){
        //this method initiates the land, (fill).
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                spots.add(new Spot(i, j, this));
            }
        }
    }

    public Bank getBank(){
        return bank;
    }
    public Spot getSpot(int row, int col){
        return spots.get(row * WIDTH + col);
    }


    public boolean validCoordinates(int row, int col){
        //this method checks if the given coordinates are valid.
        return row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH;
    }

    public Iterator<Entity> getEntities(){
        //this method returns an iterator of the creatures,
        //It is a good way to ensure abstraction.
        return creatures.iterator();
    }
}
