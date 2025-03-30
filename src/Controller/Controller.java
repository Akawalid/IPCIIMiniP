package Controller;

import Model.Entity;
import Model.EntityMovementThread;
import Model.Farm;
import Model.Resources.Resource;
import Model.Shepherd.Shepherd;
import Model.Exceptions.UnauthorizedAction;
import Model.FarmAnimals.FarmAnimal;
import Model.Spot;
import View.Land;
import View.World;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.Queue;


public class Controller {
    private Farm farm;
    private World world;

    public Controller(Farm farm, World world) {
        this.farm = farm;
        this.world = world;
        world.connect(this);
    }

    public MouseAdapter getShepherdMoveHandler() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                world.setInMovementChoiceState(true);
            }
        };
    }

    public MouseAdapter coordinatesHandler() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int col = xOfViewToModel(e.getX());
                int row = yOfViewToModel(e.getY());

                if (world.getInMovementChoiceState()) {
                    Entity entity = farm.getSelectedEntity();
                    if (entity instanceof Shepherd && world.getInMovementChoiceState()) {
                        launchMovementThread(row, col);
                    } else {
                        //TODO
                    }
                } else {
                    Entity entity = farm.getEntityInSpot(row, col);
                    if (farm.getSelectedEntity() == entity) return;
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

    private void launchMovementThread(int destRow, int destColum) {
        //TODO: le prof nous a dit de réflicher en terme du modèle, donc dans ce cas
        // Est ce qu'on doit déplacer cette méthode (ou une partie) vers le modèle?
        Queue<Spot> queue = farm.getPathFinder().findPath(
                farm.getSelectedEntity().getPosition(),
                farm.getSpot(destRow, destColum)
        );

        farm.getSelectedEntity().setPath(queue);

        int order = queue.size();
        for(Spot s: queue){
            //Highlight the path in land
            order--;
            world.getLand().addSpotEntity(s, farm.getSelectedEntity(), order);
        }

        //We unblock the screen for the user
        world.setInMovementChoiceState(false);

        (new EntityMovementThread(farm.getSelectedEntity())).start();
    }

    public MouseAdapter getFarmAnimalSellHandler(FarmAnimal fa) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.print("Animal sold");
                try {
                    farm.getBank().deposit(fa.get_selling_price());
                } catch (UnauthorizedAction s) {
                    //TODO
                }
            }
        };
    }

    public ActionListener getResourceCollectHandler(Resource r) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.printf("Resource %s collected", r.get_name());
                try {
                    r.collect();
                } catch (Exception ex) {
                    //TODO
                }
            }
        };
    }

    //TODO : est-ce que c'est bien ce genre de fonction qu'on veut créer pour commencer les threads ?
    public void start_thread(Thread t){
        t.start();
    }

}