package View.ControlPanelComponents.Information;

import Model.Shepherd.Shepherd;
import View.ControlPanelComponents.Information.InformationPanel;

import javax.swing.*;
import java.awt.*;

public class ShepherdInformationPanel extends InformationPanel {

    //constructeur
    public ShepherdInformationPanel(Shepherd s){
        setLayout(new BorderLayout());

        // Display Shepherd's name
        JLabel nameLabel = new JLabel("Name: " + s.getName());
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel, BorderLayout.NORTH);
    }


    @Override
    public void update() {
        //TODO
    }
}
