package View.ControlPanelComponents.Information;

import Model.Entity;
import Model.FarmAnimals.FarmAnimal;
import Controller.Controller;
import Model.Shepherd.Shepherd;
import View.EntityMetaData;

import javax.swing.*;
import java.awt.*;

public abstract class InformationPanel extends JPanel {

    protected static final int MARGIN = 5;
    protected JLabel status;
    protected EntityMetaData mtd;
    public InformationPanel(Entity e, EntityMetaData mtd){
        this.mtd = mtd;
        JLabel speciesLabel = new JLabel(e.getSpecies() + e.getId());
        speciesLabel.setForeground(Color.BLACK);
        add(speciesLabel, BorderLayout.NORTH);

        status = new JLabel("Status: " + mtd.getStatus());
        status.setForeground(Color.BLACK);
        add(status, BorderLayout.NORTH);

    }

    public abstract void connect(Controller c);

    public abstract void update();

}
