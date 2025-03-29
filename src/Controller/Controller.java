package Controller;

import Model.Entity;
import Model.EntityMovementThread;
import Model.Farm;
import Model.Shepherd.Shepherd;
import Model.Exceptions.UnauthorizedAction;
import Model.FarmAnimals.FarmAnimal;
import Model.Spot;
import View.Land;
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
                world.setInMovementChoiceState(true);
            }
        };
    }

    public MouseAdapter coordinatesHandler(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int col = xOfViewToModel(e.getX());
                int row = yOfViewToModel(e.getY());

                if(world.getInMovementChoiceState()) {
                    System.out.println("zzzzzzzzzzzzzzzzz");
                    Entity entity = farm.getSelectedEntity();
                    if (entity instanceof Shepherd && world.getInMovementChoiceState() ){
                        launchShepherdsMovementThread(row, col);
                    } else {
                        //TODO
                    }
                }
                else {
                    Entity entity = farm.getEntityInSpot(row, col);
                    if(farm.getSelectedEntity() == entity) return;
                    farm.setSelectedEntity(entity);
                    world.inform(World.UPDATE_ACTIVE_ENTITY);
                }
            }
        };
    }
    private int xOfViewToModel(int x) {
        return Farm.WIDTH - Math.floorDiv(x, Land.CELL_SIZE) - 1;
    }

    private int yOfViewToModel(int y) {
        return Math.floorDiv(y, Land.CELL_SIZE);
    }

    private void launchShepherdsMovementThread(int destRow, int destColum){

        int sourceRow = farm.getSelectedEntity().getPosition().getRow();
        int sourceCol = farm.getSelectedEntity().getPosition().getCol();

        System.out.println(sourceRow + ", " + sourceCol + ", " + destRow + ", " + destColum );
        farm.getSelectedEntity().setPath(farm.getPathFinder().findPath(
                farm.getSpot(sourceRow, sourceCol),
                farm.getSpot(destRow, destColum)
        ));

        //We give to the user the possibility to apply other actions.
        world.setInMovementChoiceState(false);
        (new EntityMovementThread(farm.getSelectedEntity())).start();
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