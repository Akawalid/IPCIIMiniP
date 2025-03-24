package Controller;

import Model.Entity;
import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.FarmAnimals.FarmAnimal;
import View.World;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    private Farm farm;
    private World world;
    public Controller(Farm farm, World world) {
        this.farm = farm;
        this.world = world;
        world.connect(this);
    }

    public MouseAdapter getShepherdMoveHandler(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.print("aaaa");
                if(!farm.getSelectedEntity().getThread(0).isAlive()) farm.getSelectedEntity().getThread(0).start();
            }
        };

    }

    public MouseAdapter getFarmAnimalSellHandler(FarmAnimal fa){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.print("Animal sold");
                try {
                    farm.getBank().deposit(fa.get_selling_price());
                }
                catch (UnauthorizedAction s){
                    //TODO
                }
            }
        };
    }

}