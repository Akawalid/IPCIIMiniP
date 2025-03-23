package View;

import Model.Farm;
import Model.FarmAnimals.FarmAnimal;
import Model.FarmAnimals.Sheep;
import Model.Shepherd.Shepherd;
import View.FarmAnimal.FarmAnimalActionPanel;
import View.FarmAnimal.FarmAnimalInformationPanel;
import View.Shepherd.ShepherdActionPanel;
import View.Shepherd.ShepherdInformationPanel;
import Controller.Controller;

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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //We add a random entity for the moment
        //setActiveEntity(new Shepherd("John"));
        setActiveEntity(new Sheep("Lucie"));

        setGameStatePanel();
    }

    public void setActiveEntity(Shepherd e){
        //Active entity represents the entity on which the user has clicked on.
        //the polymorphism, will choose this methode for the shepherd's entities.
        if(e == null) return;
        informationPanel = new ShepherdInformationPanel(e);
        actionPanel = new ShepherdActionPanel(e);
        removeAll();
        add(informationPanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));
        add(actionPanel);
    }

    public void setActiveEntity(FarmAnimal fa){
        //Active entity represents the entity on which the user has clicked on.
        //the polymorphism, will choose this methode for the shepherd's entities.
        if(fa == null) return;
        informationPanel = new FarmAnimalInformationPanel(fa);
        actionPanel = new FarmAnimalActionPanel(fa);
        removeAll();
        add(informationPanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));
        add(actionPanel);
    }

    /*
    for the ewe's, we call this function by polymorphism
        public void setActiveEntity(Ewe e){}
    }
    */

    private void setGameStatePanel(){
        //this method displays the game state,in which we count, the store, the bank, the day and the objective
        gameStatePanel = new JPanel();
        gameStatePanel.setBackground(Color.WHITE);
        gameStatePanel.setLayout(new BoxLayout(gameStatePanel, BoxLayout.Y_AXIS));
        gameStatePanel.add(new JLabel("Game State"));
        gameStatePanel.add(new JLabel("Day: 1"));
        gameStatePanel.add(new JLabel("Objective: 20k"));
        gameStatePanel.add(new JLabel("Balance:" +  farm.getBank().getBalance()));


        CustomButton storeButton = new CustomButton("Store");

        //when we click on the store button, a pop up window, shows up, with the items available in the store.
        storeButton.addActionListener(e -> {
            ((World) getParent().getParent()).showStore();
        });
        gameStatePanel.add(storeButton);

        add(gameStatePanel, 0);
    }

    public void connect(Controller c){

    }
}
