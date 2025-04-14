package View.World.ControlPanelComponents;

import Model.Entities.Entity;
import Model.Farm;
import Model.Entities.FarmAnimals.FarmAnimal;
import Model.Entities.Shepherd;

import Controller.WorldController;

import View.World.ControlPanelComponents.Action.ActionPanel;
import View.World.ControlPanelComponents.Action.FarmAnimalActionPanel;
import View.World.ControlPanelComponents.Action.ShepherdActionPanel;
import View.World.ControlPanelComponents.Information.FarmAnimalInformationPanel;
import View.World.ControlPanelComponents.Information.InformationPanel;
import View.World.ControlPanelComponents.Information.ShepherdInformationPanel;
import View.World.World;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JSplitPane {
    //This class represents the panel that comes on the left side of the screen, it will contain the entities information
    // and actions.

    //MARGIN, represents the margin between the information, actions panels and the whole ControlPanel
    public static final int W = 250, H = 700;

    private final GameStatePanel gameStatePanel;
    //first part of the panel is the information panel, on top

    private final MarketPanel marketPanel;
    private InformationPanel informationPanel;
    //second part of the panel is the action panel, on the bottom
    private ActionPanel actionPanel;

    private WorldController controller;
    private final Farm farm;
    private final World world;
    private JSplitPane sp, sp2;


    public ControlPanel(Farm farm, World world){
        super(JSplitPane.VERTICAL_SPLIT);

        this.farm = farm;
        this.world = world;
        setPreferredSize(new Dimension(W, H));

        //State of the game
        gameStatePanel = new GameStatePanel(farm);
        gameStatePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        gameStatePanel.setPreferredSize(new Dimension(W, 100));
        gameStatePanel.setBackground(Color.red);

        //Market
        marketPanel = new MarketPanel(world);
        marketPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        marketPanel.setPreferredSize(new Dimension(W, 100));

        add(gameStatePanel, JSplitPane.TOP);
        sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setTopComponent(marketPanel);
        add(sp, JSplitPane.BOTTOM);
        sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setBottomComponent(sp2);
    }

    public void updateActiveEntity() {

        if(informationPanel != null){
            sp2.remove(informationPanel);
        }
        if(actionPanel != null){
            sp2.remove(actionPanel);
        }

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

            //in case e is not null
            sp2.add(informationPanel, JSplitPane.TOP);
            sp2.add(actionPanel, JSplitPane.BOTTOM);
        }
    }

    public void connect(WorldController c){
        //Invariant: connect should be called for the first time by the controller
        //We can say that by a strengthened condition as below.
        assert(c != null);
        controller = c;

        marketPanel.connect(c);
        gameStatePanel.connect(c);

        if(informationPanel != null) informationPanel.connect(controller);
        if(actionPanel != null) actionPanel.connect(controller);
    }
}
