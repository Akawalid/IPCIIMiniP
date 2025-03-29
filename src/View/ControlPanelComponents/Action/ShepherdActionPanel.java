package View.ControlPanelComponents.Action;

import Model.Shepherd.Shepherd;
import Controller.Controller;
import View.CustomButton;

import javax.swing.*;
import java.awt.*;

public class ShepherdActionPanel extends ActionPanel {
    private CustomButton moveButton;

    public ShepherdActionPanel(Shepherd s){

        setBackground(new Color(0x03071e));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        moveButton = new CustomButton("Move");
        add(moveButton);

        add(Box.createRigidArea(new Dimension(0, MARGIN)));

        /* Hum, la collecte se fait sur les animaux directement, et l'achat d'animaux aussi.
        Donc je ne pense pas que ces boutons servent.

        //We didn't add these functionnalities yet, so we remove them from the screen
        collectButton = new CustomButton("Collect");
        //add(collectButton);

        add(Box.createRigidArea(new Dimension(0, MARGIN)));

        putButton = new CustomButton("Put");
        //add(putButton);
        */

    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public void connect(Controller c){
        moveButton.addMouseListener(c.getShepherdMoveHandler());
    }
}
