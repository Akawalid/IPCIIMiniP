package Controller;

import Model.Entities.CleanDeadEntitiesThread;
import Model.Entities.FarmAnimals.Sheep;
import Model.Entities.FarmAnimals.UpdateAgeThread;
import Model.Entities.Shepherd;
import Model.Farm;
import View.MainMenu.MainMenu;
import View.PrincipalPanel;
import View.World.PlaySound;
import View.World.World;

public class Controller {
    public static Shepherd SHEPHERD_TEST_1;
    public static Sheep SHEEP_TEST_1;
    private PrincipalPanel view;
    public Controller(PrincipalPanel view) {
        this.view = view;
        MainMenu mainmenu = new MainMenu();
        new MainMenuController(mainmenu, this);
        view.add("MainMenu", mainmenu);
    }

    public PrincipalPanel getView() {
        return view;
    }
}
