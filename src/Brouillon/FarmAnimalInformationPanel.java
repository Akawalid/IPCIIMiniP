package Brouillon;

import Model.FarmAnimals.FarmAnimal;
import Model.Resources.Resource;
import View.ControlPanelComponents.Information.ResourceBarPanel;

import javax.swing.*;
import java.util.Iterator;

public class FarmAnimalInformationPanel extends JPanel {

    FarmAnimal animal;

    public FarmAnimalInformationPanel(FarmAnimal f){
        super();

        this.animal = f;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Iterator<Resource> it = animal.getResources(); it.hasNext(); ) {
            Resource r = it.next();
            this.add(new ResourceBarPanel(r));
        }

    }

}
