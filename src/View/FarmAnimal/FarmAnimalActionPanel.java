package View.FarmAnimal;

import Model.FarmAnimals.FarmAnimal;

import javax.swing.*;

public class FarmAnimalActionPanel extends JPanel {

    //créer un constructeur vide
    public FarmAnimalActionPanel(FarmAnimal fa) {
        //créer un layout pour afficher les boutons
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //vendre l'animal
        JButton sellButton = new JButton("Sell");
        add(sellButton);
        //TODO
    }
}
