package View.ControlPanelComponents;

import Model.Entity;
import Model.Farm;
import Model.FarmAnimals.FarmAnimal;
import Model.Shepherd.Shepherd;

import Controller.Controller;

import View.ControlPanelComponents.Action.ActionPanel;
import View.ControlPanelComponents.Action.FarmAnimalActionPanel;
import View.ControlPanelComponents.Action.ShepherdActionPanel;
import View.ControlPanelComponents.Information.FarmAnimalInformationPanel;
import View.ControlPanelComponents.Information.InformationPanel;
import View.ControlPanelComponents.Information.ShepherdInformationPanel;
import View.EntityMetaData;
import View.World;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ControlPanel extends JPanel {
    //This class represents the panel that comes on the left side of the screen, it will contain the entities information
    // and actions.

    //MARGIN, represents the margin between the information, actions panels and the whole ControlPanel
    public static final int W = 250, H_INFO_PANEL = 200, H_ACTION_PANEL = 200, MARGIN = 10;

    private GameStatePanel gameStatePanel;
    //first part of the panel is the information panel, on top

    private MarketPanel marketPanel;
    private InformationPanel informationPanel;
    //second part of the panel is the action panel, on the bottom
    private ActionPanel actionPanel;

    private Controller controller;
    private Farm farm;
    private final HashMap<Entity, EntityMetaData> entitiesMetaData;
    private World world;


    public ControlPanel(Farm farm, HashMap<Entity, EntityMetaData> entitiesMetaData, World world ){
        super();
        this.farm = farm;
        this.entitiesMetaData = entitiesMetaData;
        this.world = world;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        gameStatePanel = new GameStatePanel(farm);
        marketPanel = new MarketPanel(world);

        add(gameStatePanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));
        add(marketPanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));

        //Paneau d'information + d'action
        //setActiveEntity(null);
    }

    public void updateActiveEntity() {

        if(informationPanel != null) remove(informationPanel);
        if(actionPanel != null) remove(actionPanel);

        Entity e = farm.getSelectedEntity();
        switch (e) {
            case null -> {
                informationPanel = null;
                actionPanel = null;
                return;
            }
            case Shepherd s -> {
                informationPanel = new ShepherdInformationPanel(s);
                actionPanel = new ShepherdActionPanel(s);
            }
            case FarmAnimal fa -> {
                informationPanel = new FarmAnimalInformationPanel(fa);
                actionPanel = new FarmAnimalActionPanel(fa);
            }
            default -> throw new IllegalStateException("Case not supported");
        }

        //in case e is not null
        add(informationPanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));
        add(actionPanel);

        //When we upload ControlePanel to the ui, the controller attribute is set null
        //Therefore, the method connect, should be called for the first time by the controller
        if(controller != null) connect(controller);
    }

    public void connect(Controller c){
        //Invariant: connect should be called for the first time by the controller
        //We can say that by a strengthened condition as below.
        assert(c != null);
        controller = c;

        marketPanel.connect(controller);
        if(informationPanel != null) informationPanel.connect(controller);
        if(actionPanel != null) actionPanel.connect(controller);
    }
}
