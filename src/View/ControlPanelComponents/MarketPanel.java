package View.ControlPanelComponents;

//import View.ControlPanelComponents.Information.PurchaseType;
import View.ControlPanelComponents.Information.PurchaseType;
import View.CustomButton;
import View.World;
import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketPanel extends JPanel {
    private World world;

    private CustomButton buySheepButton;
    private CustomButton buyEweButton;
    private CustomButton buyHenButton;
    private CustomButton hireShepherdButton;

    public MarketPanel(World world) {
        this.world = world;
        // Create a layout to display the buttons
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add the word "Market"
        JLabel marketLabel = new JLabel("Market");
        add(marketLabel);

        // Buy an ewe
        buyEweButton = new CustomButton("Buy an ewe");
        add(buyEweButton);

        // Buy a sheep
        buySheepButton = new CustomButton("Buy a sheep");
        add(buySheepButton);

        // Buy a hen
        buyHenButton = new CustomButton("Buy a hen");
        add(buyHenButton);

        // Hire a shepherd
        hireShepherdButton = new CustomButton("Hire a shepherd");
        add(hireShepherdButton);
    }

    public void connect(Controller c){
        buyEweButton.addActionListener(c.getEweBuyHandler());
        buySheepButton.addActionListener(c.getSheepBuyHandler());
        buyHenButton.addActionListener(c.getHenBuyHandler());
        hireShepherdButton.addActionListener(c.getShepherdHireHandler());
    }
}