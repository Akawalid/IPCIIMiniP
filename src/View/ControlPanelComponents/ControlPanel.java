package View.ControlPanelComponents;

import Model.Entity;
import Model.Farm;
import Model.FarmAnimals.FarmAnimal;
import Model.FarmAnimals.Sheep;
import Model.Shepherd.Shepherd;

import Controller.Controller;

import View.ControlPanelComponents.Action.ActionPanel;
import View.ControlPanelComponents.Action.FarmAnimalActionPanel;
import View.ControlPanelComponents.Action.ShepherdActionPanel;
import View.ControlPanelComponents.Information.FarmAnimalInformationPanel;
import View.ControlPanelComponents.Information.InformationPanel;
import View.ControlPanelComponents.Information.ShepherdInformationPanel;
import View.Main;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    //This class represents the panel that comes on the left side of the screen, it will contain the entities information
    // and actions.

    //MARGIN, represents the margin between the information, actions panels and the whole ControlPanel
    public static final int W = 250, H_INFO_PANEL = 200, H_ACTION_PANEL = 200, MARGIN = 10;

    private JPanel gameStatePanel;
    //first part of the panel is the information panel, on top

    private JPanel marketPanel;
    private InformationPanel informationPanel;
    //second part of the panel is the action panel, on the bottom
    private ActionPanel actionPanel;

    private Controller controller;
    private Farm farm;

    public ControlPanel(Farm farm){
        super();
        this.farm = farm;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Etat du jeu et marché
        gameStatePanel = new gameStatePanel(farm);
        marketPanel = new MarketPanel();

        add(gameStatePanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));
        add(marketPanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));

        //Paneau d'information + d'action
        //setActiveEntity(null);

        //TODO, change : We add a random entity for the moment
//        setActiveEntity(Main.SHEPHERD_TEST_1);
        //setActiveEntity(Main.SHEEP_TEST_1);
    }

    public void updateActiveEntity() {
        if(informationPanel != null) remove(informationPanel);
        if(actionPanel != null) remove(actionPanel);

        Entity e = farm.getSelectedEntity();
        if(e == null){
            informationPanel = null;
            actionPanel = null;
        }
        else{
            if(e instanceof Shepherd) {
                informationPanel = new ShepherdInformationPanel((Shepherd)e);
                actionPanel = new ShepherdActionPanel((Shepherd)e);
            }
            else if(e instanceof FarmAnimal){
                informationPanel = new FarmAnimalInformationPanel((FarmAnimal)e);
                actionPanel = new FarmAnimalActionPanel((FarmAnimal)e);
            }
            else{
                throw new IllegalStateException("Case not supported");
            }

            //When we upload ControlePanel to the ui, the controller attribute is set null
            //Therefor, the method connect, should be called for the first time by the controller
            if(controller != null) connect(controller);

            add(informationPanel);
            add(Box.createRigidArea(new Dimension(0, MARGIN)));
            add(actionPanel);
        }
    }

    public void connect(Controller c){
        //Invariant: connect should be called for the first time by the controller
        //We can say that by a strengthened condition as below.
        assert(c != null);
        controller = c;
        if(informationPanel != null) informationPanel.connect(controller);
        if(actionPanel != null) actionPanel.connect(controller);
    }
}
