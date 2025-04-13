package View.World.ControlPanelComponents.Information;

import Model.Shepherd.Shepherd;

import java.awt.*;
import Controller.WorldController;
//import View.EntityMetaData;

public class ShepherdInformationPanel extends InformationPanel {

    public ShepherdInformationPanel(Shepherd s){
        super(s);
        setLayout(new BorderLayout());
    }

    @Override
    public void connect(WorldController c){
        //TODO
    }

    /*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        status.setText("Status: " + mtd.getStatus());
    }*/
}
