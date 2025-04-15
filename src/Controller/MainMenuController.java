package Controller;

import Model.Farm;
import Model.Entities.FarmAnimals.Sheep;
import Model.Entities.FarmAnimals.UpdateAgeThread;
import Model.Entities.Shepherd;
import View.MainMenu.MainMenu;
import View.World.PlaySound;
import View.World.World;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuController {
    public static Shepherd SHEPHERD_TEST_1;
    public static Sheep SHEEP_TEST_1;
    private MainMenu mainmenu;
    private Controller genController;
    public MainMenuController(MainMenu mainmenu, Controller generalController) {
        this.mainmenu = mainmenu;
        this.genController = generalController;

        this.mainmenu.connect(this);

    }
    public MouseAdapter getStartButtonListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Farm farm = new Farm();

                SHEPHERD_TEST_1 = new Shepherd(farm.getSpot(0, 0), farm);
                farm.addEntity(SHEPHERD_TEST_1);
                SHEEP_TEST_1 = new Sheep(farm.getSpot(5, 5));
                farm.addEntity(SHEEP_TEST_1);


                //create obstacles on the map
                farm.getSpot(1, 1).setIsTraversable(false);
                farm.getSpot(2, 2).setIsTraversable(false);
                farm.getSpot(2, 3).setIsTraversable(false);

                // Initialiser les dens de prédateurs (Génère des dens de loups et de renards qui spawneront leurs prédateurs)
                farm.initPredators();
                World world = new World(farm);
                new WorldController(farm, world);
                // Lancement du thread de mise à jour de la simulation
                // Ce thread parcourt toutes les entités de la ferme et appelle updateAge() pour chaque animal.
                UpdateAgeThread simThread = new UpdateAgeThread(farm);
                simThread.start();
                genController.getView().add("World", world);
                genController.getView().showCard("World");
                PlaySound ps = new PlaySound(farm);
                ps.start();
            }
        };
    }
}
