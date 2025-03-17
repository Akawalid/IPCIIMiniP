package View.ControlPanelComponents.Action;

import Controller.ReactionMoveShepherd;
import Model.Shepherd.Shepherd;
import View.ControlPanelComponents.Action.ActionPanel;
import View.CustomButton;

import javax.swing.*;
import java.awt.*;

public class ShepherdActionPanel extends ActionPanel {

    public ShepherdActionPanel(Shepherd s){

        setBackground(new Color(0x03071e));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        CustomButton moveButton = new CustomButton("Move");
        moveButton.addMouseListener(new ReactionMoveShepherd(s));
        add(moveButton);

        add(Box.createRigidArea(new Dimension(0, MARGIN)));

        CustomButton collectButton = new CustomButton("Collect");
        add(collectButton);

        add(Box.createRigidArea(new Dimension(0, MARGIN)));

        CustomButton putButton = new CustomButton("Put");
        add(putButton);

    }

    @Override
    public void update() {
        //TODO
    }
}
