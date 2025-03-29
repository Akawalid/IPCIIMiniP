package View.ControlPanelComponents.Information;

import Controller.Controller;
import Model.FarmAnimals.FarmAnimal;
import Model.Resources.Resource;
import View.ControlPanelComponents.Information.InformationPanel;
import View.ControlPanelComponents.Information.ResourceBarPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FarmAnimalInformationPanel extends InformationPanel {

    FarmAnimal animal;
    ArrayList<ResourceBarPanel> resourcePanels;

    //constructeur
    public FarmAnimalInformationPanel(FarmAnimal fa) {
        animal = fa;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        resourcePanels = new ArrayList<>();
        //map chaque ressource à un ResourceBarPanel dans une nouvelle variable Iterator
        for ( Iterator<Resource> it = animal.getResources(); it.hasNext(); ) {
            Resource r = it.next();
            ResourceBarPanel rbp = new ResourceBarPanel(r);
            resourcePanels.add(rbp);
            this.add(rbp);
        }

    }

    @Override
    public void connect(Controller c) {
        for(ResourceBarPanel rbp: resourcePanels){
            rbp.connect(c);
        };

    }

    @Override
    public void update() {
        for (ResourceBarPanel rbp : resourcePanels) {
            rbp.update();
        }
    }
}
