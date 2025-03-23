package View.ControlPanelComponents;

import View.CustomButton;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketPanel extends JPanel {

    // Create a constructor
    public MarketPanel() {
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
            }
        });
        add(hireShepherdButton);
    }
}
