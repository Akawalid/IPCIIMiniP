package Controller;

import Model.Entity;
import Model.Farm;
import Model.FarmAnimals.Ewe;
import Model.FarmAnimals.Hen;
import Model.FarmAnimals.Sheep;
import Model.Resources.Resource;
import Model.Shepherd.Shepherd;
import Model.Exceptions.UnauthorizedAction;
import Model.FarmAnimals.FarmAnimal;
import Model.Spot;
import View.ControlPanelComponents.Information.PurchaseType;
import View.Land;
import View.World;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;


public class Controller {
    private final Farm farm;
    private final World world;

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
                // Conversion des coordonnées en pixels en indices de la grille du modèle
                int col = xOfViewToModel(e.getX());
                int row = yOfViewToModel(e.getY());

                // 1. Gestion du mode d'achat (purchase mode)
                if (world.getPurchaseMode() != null) {
                    Spot spot = farm.getSpot(row, col);
                    if (spot.isTraversable()) {
                        Entity newEntity = null;
                        switch (world.getPurchaseMode()) {
                            case EWE:
                                newEntity = new Ewe(spot);
                                break;
                            case SHEEP:
                                newEntity = new Sheep(spot);
                                break;
                            case HEN:
                                newEntity = new Hen(spot);
                                break;
                            default:
                                System.out.println("Unknown purchase type.");
                        }
                        if (newEntity != null) {
                            farm.addEntity(newEntity);
                            spot.setIsTraversable(false); // Marquer le spot comme occupé
                            farm.getBank().withdraw(newEntity.get_buying_price());
                            System.out.println(world.getPurchaseMode() + " placed at (" + row + ", " + col + ")");
                        }
                        // Réinitialiser le mode d'achat et redessiner la grille
                        world.setPurchaseMode(null);
                        world.getLand().repaint();
                    } else {
                        System.out.println("This spot is not available. Please choose another one.");
                    }
                    return; // On termine ici pour ce clic si le mode d'achat était actif
                }


                // 2. Si le mode de déplacement est activé, on traite le déplacement
                if (world.getInMovementChoiceState()) {
                    Entity entity = farm.getSelectedEntity();
                    if (entity instanceof Shepherd) {
                        launchMovementThread(row, col);
                    } else {
                        // TODO: gérer le déplacement pour d'autres types d'entités si nécessaire
                    }
                } else {
                    // 3. Sinon, le clic est interprété comme une sélection d'entité
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

        if(farm.getSelectedEntity().getThread() == null || !farm.getSelectedEntity().getThread().isAlive()){
            farm.getSelectedEntity().startNewThread();
        }

    }

    public MouseAdapter getFarmAnimalSellHandler(FarmAnimal fa) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.print("Animal sold");
                try {
                    farm.getBank().deposit(fa.get_selling_price());
                    //farm.remove(fa);
                } catch (UnauthorizedAction s) {
                    //TODO
                }
            }
        };
    }

    public ActionListener getEweBuyHandler() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the "Buy an ewe" button is clicked
                System.out.println("An ewe has been bought.");
                // Set the purchase mode to EWE
                world.setPurchaseMode(PurchaseType.EWE);

            }
        };
    }

    public ActionListener getSheepBuyHandler() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the "Buy a sheep" button is clicked
                System.out.println("A sheep has been bought.");
                // Set the purchase mode to SHEEP
                world.setPurchaseMode(PurchaseType.SHEEP);
            }
        };
    }

    public ActionListener getHenBuyHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the "Buy a hen" button is clicked
                System.out.println("A hen has been bought.");
                // Set the purchase mode to HEN
                world.setPurchaseMode(PurchaseType.HEN);
            }
        };
    }

    public ActionListener getShepherdHireHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the "Hire a shepherd" button is clicked
                System.out.println("A shepherd has been hired.");
                // Set the purchase mode to SHEPHERD
                world.setPurchaseMode(PurchaseType.SHEPHERD);
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
                    farm.getBank().deposit(r.get_selling_price());
                } catch (Exception ex) {
                    //TODO
                }
            }
        };
    }


}