package View.ControlPanelComponents;

//import View.ControlPanelComponents.Information.PurchaseType;
import View.ControlPanelComponents.Information.PurchaseType;
import View.CustomButton;
import View.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketPanel extends JPanel {
    private World world;
    // Create a constructor


    public MarketPanel(World world) {
        this.world = world;
        // Create a layout to display the buttons
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add the word "Market"
        JLabel marketLabel = new JLabel("Market");
        add(marketLabel);

        // Buy a sheep
        CustomButton buySheepButton = new CustomButton("Buy a sheep");
        buySheepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the "Buy a sheep" button is clicked
                System.out.println("A sheep has been bought.");
                // Set the purchase mode to SHEEP
                world.setPurchaseMode(PurchaseType.SHEEP);
            }
        });
        add(buySheepButton);

        // Buy an ewe
        CustomButton buyEweButton = new CustomButton("Buy an ewe");
        buyEweButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the "Buy an ewe" button is clicked
                System.out.println("An ewe has been bought.");
                // Set the purchase mode to EWE
                world.setPurchaseMode(PurchaseType.EWE);

            }
        });
        add(buyEweButton);

        // Buy a hen

        CustomButton buyHenButton = new CustomButton("Buy a hen");
        buyHenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the "Buy a hen" button is clicked
                System.out.println("A hen has been bought.");
                // Set the purchase mode to HEN
                world.setPurchaseMode(PurchaseType.HEN);
            }
        });
        add(buyHenButton);

        // Hire a shepherd
        CustomButton hireShepherdButton = new CustomButton("Hire a shepherd");
        hireShepherdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the "Hire a shepherd" button is clicked
                System.out.println("A shepherd has been hired.");
                // Set the purchase mode to SHEPHERD
                world.setPurchaseMode(PurchaseType.SHEPHERD);
            }
        });
        add(hireShepherdButton);
    }
}