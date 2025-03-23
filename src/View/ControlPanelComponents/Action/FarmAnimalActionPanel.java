package View.ControlPanelComponents.Action;

import Model.FarmAnimals.FarmAnimal;
import Controller.Controller;
import View.ControlPanelComponents.Action.ActionPanel;

import javax.swing.*;

public class FarmAnimalActionPanel extends ActionPanel {

    //constructeur
    public FarmAnimalActionPanel(FarmAnimal e){
        //créer un layout pour afficher les boutons
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //vendre l'animal
        JButton sellButton = new JButton("Sell");
        add(sellButton);
        //TODO implémenter listener
    }

    @Override
    public void update() {
        //TODO
    }

    public void connect(Controller c){
    }
}
