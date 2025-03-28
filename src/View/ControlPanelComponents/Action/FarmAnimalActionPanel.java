package View.ControlPanelComponents.Action;

import Model.Exceptions.UnauthorizedAction;
import Model.FarmAnimals.FarmAnimal;
import Controller.Controller;
import View.ControlPanelComponents.Action.ActionPanel;
import View.CustomButton;

import javax.swing.*;

public class FarmAnimalActionPanel extends ActionPanel {

    //constructeur
    public FarmAnimalActionPanel(FarmAnimal e, Controller c){
        //créer un layout pour afficher les boutons
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //vendre l'animal
        CustomButton sellButton = new CustomButton("Sell");
        add(sellButton);
        sellButton.addMouseListener(c.getFarmAnimalSellHandler(e));
    }

    @Override
    public void update() {
        //TODO
    }
}
