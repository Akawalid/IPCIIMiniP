package Controller;

import Model.Entity;
import Model.EntityMovementThread;
import Model.Farm;
import Model.Shepherd.Shepherd;
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
                int destRow=0;
                int destCol=0;
                Entity entity = farm.getSelectedEntity();
                if (entity instanceof Shepherd && world.getInMovementChoiceState() ){
                    launchShepherdsMovementThread(destRow, destCol);
                } else {
                    //TODO
                }
            }
        };
    }

    private void launchShepherdsMovementThread(int destRow, int destColum){

        int sourceRow = farm.getSelectedEntity().getPosition().getRow();
        int sourceCol = farm.getSelectedEntity().getPosition().getCol();

        farm.getPathFinder().findPath(
                farm.getSpot(sourceRow, sourceCol),
                farm.getSpot(destRow, destColum)
        );

        //We give to the user the possibility to apply other actions.
        world.setInMovementChoiceState(false);
        (new EntityMovementThread(farm.getSelectedEntity())).start();
    }

}