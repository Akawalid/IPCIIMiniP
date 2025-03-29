package View.ControlPanelComponents.Information;

import Model.FarmAnimals.FarmAnimal;
import Controller.Controller;

import javax.swing.*;

public abstract class InformationPanel extends JPanel {

    private static final int MARGIN = 5;

    private void construct(FarmAnimal fa){


    }
    //TODO: Abstract
    public void connect(Controller c){};
    public abstract void update();

}
