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

    }

    @Override
    public void connect(Controller c){
        moveButton.addMouseListener(c.getShepherdMoveHandler());
    }
}
