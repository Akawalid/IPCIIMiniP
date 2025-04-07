package View.ControlPanelComponents;
import Model.Farm;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class GameStatePanel extends JPanel {

    private static final JLabel PANEL_NAME = new JLabel("Game state");

    private Farm farm;
    private JLabel day;
    private JLabel objective;
    private JLabel balance;


    //constructeur
    public GameStatePanel(Farm f){
        //this method displays the game state,in which we count, the store, the bank, the day and the objective
        farm = f;

        //TODO : update with (to create:) timing class and update Bank
        day = new JLabel("Day: 1");
        objective = new JLabel("Objective: 20k");
        balance = new JLabel("Balance:" +  farm.getBank().getBalance());

        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(PANEL_NAME);
        add(day);
        add(objective);
        add(balance);

        /* CustomButton storeButton = new CustomButton("Store");
        //when we click on the store button, a pop up window, shows up, with the items available in the store.
        storeButton.addActionListener(e -> {
            ((World) getParent().getParent()).showStore();
        });
        add(storeButton);
        */
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        balance.setText("Balance:" + farm.getBank().getBalance());
    }

    public void connect(Controller c){
        //TODO
    }
}
