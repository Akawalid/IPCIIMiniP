package View.World.ControlPanelComponents.Information;

import Controller.WorldController;
import Model.Entities.FarmAnimals.FarmAnimal;
import Model.Resources.Resource;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FarmAnimalInformationPanel extends InformationPanel {

    FarmAnimal animal;
    ArrayList<ResourceBarPanel> resourcePanels;

    //constructeur
    public FarmAnimalInformationPanel(FarmAnimal fa) {
        super(fa);
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
    public void connect(WorldController c) {
        for(ResourceBarPanel rbp: resourcePanels){
            rbp.connect(c);
        };

    }
}
