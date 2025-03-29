package View.ControlPanelComponents.Information;

import Model.Shepherd.Shepherd;

import javax.swing.*;
import java.awt.*;
import Controller.Controller;

public class ShepherdInformationPanel extends InformationPanel {

    //constructeur
    public ShepherdInformationPanel(Shepherd s){
        setLayout(new BorderLayout());

        // Display Shepherd's name
        JLabel speciesLabel = new JLabel(s.getSpecies() + s.getId());
        speciesLabel.setForeground(Color.WHITE);
        add(speciesLabel, BorderLayout.NORTH);
    }

    @Override
    public void connect(Controller c){
        //TODO
    }

    @Override
    public void update() {
        //TODO
    }
}
