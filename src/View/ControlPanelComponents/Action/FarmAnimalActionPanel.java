package View.ControlPanelComponents.Action;

import Model.FarmAnimals.FarmAnimal;
import Controller.Controller;
import View.CustomButton;

import javax.swing.*;

public class FarmAnimalActionPanel extends ActionPanel {

    private final FarmAnimal farm_animal;
    private final CustomButton sellButton;

    //constructeur
    public FarmAnimalActionPanel(FarmAnimal fa){
        //créer un layout pour afficher les boutons
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        farm_animal = fa;

        //vendre l'animal
        sellButton = new CustomButton("Sell");
        add(sellButton);
    }

    @Override
    public void connect(Controller c) {
        sellButton.addMouseListener(c.getFarmAnimalSellHandler(farm_animal));
    }

    @Override
    public void update() {
        //TODO
    }
}
