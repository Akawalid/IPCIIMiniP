package Controller;

import Model.Farm;
import Model.FarmAnimals.Sheep;
import Model.FarmAnimals.UpdateAgeThread;
import Model.Shepherd.Shepherd;
import View.MainMenu.MainMenu;
import View.PrincipalPanel;
import View.World.World;

public class Controller {
    public static Shepherd SHEPHERD_TEST_1;
    public static Sheep SHEEP_TEST_1;
    private PrincipalPanel view;
    public Controller(PrincipalPanel view) {
        this.view = view;

//        MainMenu mainmenu = new MainMenu();
//        new MainMenuController(mainmenu, this);
//        view.add("MainMenu", mainmenu);
        Farm farm = new Farm();

        SHEPHERD_TEST_1 = new Shepherd(farm.getSpot(0, 0), farm);
        farm.addEntity(SHEPHERD_TEST_1);
        SHEEP_TEST_1 = new Sheep(farm.getSpot(5, 5));
        farm.addEntity(SHEEP_TEST_1);

        // Initialiser les dens de prédateurs (Génère des dens de loups et de renards qui spawneront leurs prédateurs)
        farm.initPredators();
        World world = new World(farm);
        new WorldController(farm, world);
        // Lancement du thread de mise à jour de la simulation
        // Ce thread parcourt toutes les entités de la ferme et appelle updateAge() pour chaque animal.
        UpdateAgeThread simThread = new UpdateAgeThread(farm);
        simThread.start();
        this.view.add("World", world);
        this.view.showCard("World");
    }

    public PrincipalPanel getView() {
        return view;
    }
}
