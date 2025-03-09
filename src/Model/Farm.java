package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Farm {
    public static final int WIDTH = 100, HEIGHT = 100;
    private HashSet<Entity> entities;
    private ArrayList<Spot> spots;
    private Bank bank;

    public Farm(){
        entities = new HashSet<>();
        spots = new ArrayList<>();

        bank = new Bank();

        initLand();
    }

    private void initLand(){
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                spots.add(new Spot(j, i));
            }
        }
    }

    public Bank getBank(){
        return bank;
    }
}
