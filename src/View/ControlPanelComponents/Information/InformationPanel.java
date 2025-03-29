package View.ControlPanelComponents.Information;

import Model.FarmAnimals.FarmAnimal;
import Controller.Controller;

import javax.swing.*;

public abstract class InformationPanel extends JPanel {

    protected static final int MARGIN = 5;

    public abstract void connect(Controller c);

    public abstract void update();

}
