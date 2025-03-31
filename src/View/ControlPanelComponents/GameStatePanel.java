package View.ControlPanelComponents;
import Model.Farm;
import View.CustomButton;
import View.World;

import javax.swing.*;
import java.awt.*;

public class gameStatePanel extends JPanel {

    //constructeur
    public gameStatePanel(Farm farm){
        //this method displays the game state,in which we count, the store, the bank, the day and the objective
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Game State"));
        add(new JLabel("Day: 1"));
        add(new JLabel("Objective: 20k"));
        add(new JLabel("Balance:" +  farm.getBank().getBalance()));

        CustomButton storeButton = new CustomButton("Store");

        //when we click on the store button, a pop up window, shows up, with the items available in the store.
        storeButton.addActionListener(e -> {
            ((World) getParent().getParent()).showStore();
        });
        add(storeButton);
    }


}
