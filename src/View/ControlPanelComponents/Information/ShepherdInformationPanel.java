package View.ControlPanelComponents.Information;

import Model.Shepherd.Shepherd;

import javax.swing.*;
import java.awt.*;
import Controller.Controller;
import View.EntityMetaData;

public class ShepherdInformationPanel extends InformationPanel {

    public ShepherdInformationPanel(Shepherd s, EntityMetaData mtd){
        super(s, mtd);
        setLayout(new BorderLayout());
    }

    @Override
    public void connect(Controller c){
        //TODO
    }

    /*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        status.setText("Status: " + mtd.getStatus());
    }*/
}
