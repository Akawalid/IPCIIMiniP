package View;

import Model.Entity;
import Model.Farm;
import Model.Shepherd;
import View.Shepherd.ShepherdActionPanel;
import View.Shepherd.ShepherdInformationPanel;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    //This class represents the panel that comes on the left side of the screen, it will contain the entities information
    // and actions.

    //MARGIN, represents the margin between the information, actions panels and the whole ControlPanel
    public static final int W = 250, H_INFO_PANEL = 200, H_ACTION_PANEL = 200, MARGIN = 10;

    private JPanel gameStatePanel;
    //first part of the panel is the information panel, on top
    private JPanel informationPanel;
    //second part of the panel is the action panel, on the bottom
    private JPanel actionPanel;

    private Farm farm;
    public ControlPanel(Farm farm){
        super();
        this.farm = farm;

        setBackground(new Color(0x03071e));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setActiveEntity(new Shepherd("Robbert"));
        setGameStatePanel();
    }

    public void setActiveEntity(Entity e){
        informationPanel = new ShepherdInformationPanel((Shepherd) e);
        actionPanel = new ShepherdActionPanel();
        removeAll();
        add(informationPanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));
        add(actionPanel);
    }

    private void setGameStatePanel(){

        gameStatePanel = new JPanel();
        gameStatePanel.setBackground(Color.WHITE);
        gameStatePanel.setLayout(new BoxLayout(gameStatePanel, BoxLayout.Y_AXIS));
        gameStatePanel.add(new JLabel("Game State"));
        gameStatePanel.add(new JLabel("Day: 1"));
        gameStatePanel.add(new JLabel("Time: 12:00"));
        gameStatePanel.add(new JLabel("Weather: Sunny"));
        gameStatePanel.add(new JLabel("Bank Balance:" +  farm.getBank().getBalance()));


        CustomButton storeButton = new CustomButton("Store");
        gameStatePanel.add(storeButton);


        add(gameStatePanel, 0);
    }

    //The dimensions of the current container depends on the parent container's dimensions.
    //This methode executes once the current component is added to parent container.
    @Override
    public void addNotify() {
        super.addNotify();
        assert(getParent() != null);
        setPreferredSize(new Dimension(W, getParent().getHeight()));
    }
}
