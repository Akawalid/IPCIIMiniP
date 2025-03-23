package View.ControlPanelComponents;

import View.CustomButton;

import javax.swing.*;

public class MarketPanel extends JPanel {

    //créer un constructeur
    public MarketPanel() {
        //créer un layout pour afficher les boutons
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //ajouter le mot "Market"
        JLabel marketLabel = new JLabel("Market");
        add(marketLabel);

        //acheter un mouton
        CustomButton buySheepButton = new CustomButton("Buy a sheep");
        add(buySheepButton);
        //TODO add listener

        //acheter une brebis
        CustomButton buyEweButton = new CustomButton("Buy an ewe");
        add(buyEweButton);
        //TODO add listener

        //acheter une poule
        CustomButton buyHenButton = new CustomButton("Buy a hen");
        add(buyHenButton);
        //TODO add listener

        //recruter un shepherd
        CustomButton hireShepherdButton = new CustomButton("Hire a shepherd");
        add(hireShepherdButton);
        //TODO add listener
    }
}
