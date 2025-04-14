package Model.Entities.FarmAnimals;

import Model.Entities.Entity;
import Model.Exceptions.UnauthorizedAction;
//import Model.Entities.Predators.FoxDen;
import Model.Resources.Resource;
import Model.Position.Spot;

import java.util.ArrayList;
import java.util.Iterator;

import static Model.Entities.FarmAnimals.AgeState.*;

public abstract class FarmAnimal extends Entity {

    //------------------- Attributes -------------------//

    private final int PRICE;
    // ### Age evolution ###

    private int age;       // L'âge exprimé en nombre de cycles
    protected AgeState state;  // "Bébé", "Mature", "Vieux"

    // ### Resource production ###
    ArrayList<Resource> resourceList;

    //------------------- Methods -------------------//

    /** constructor **/
    public FarmAnimal(Spot s, int price) {
        super(s);
        //Age evolution
        // Initialiser à bébé
        this.age = 0;
        this.state = BABY;
        //Resource production
        resourceList = new ArrayList<>();
        PRICE = price;
    }

    // ### Age evolution ###

    public final void updateAge() {
        // Si l'animal est déjà mort, on ne fait rien
        if (state == DEAD) {
            return;
        }
        age++;
        if (age>3 && age<7) {
            state = MATURE;
            // TODO:  Possibilité d'augmenter la production ou la reproduction à cet état
        } else if (age > 7 && age < 10) {
            state = OLD;
            // TODO : Réduire la production et / ou la valeur de revente, par exemple
        } else if (age == 10) {
            // L'animal meurt de vieillesse
            System.out.println("L'animal est mort de vieillesse.");
            state = DEAD;
        }
        System.out.println("Âge : " + age + " - État : " + state);
    }

    public Object getState() {
        return state;
    }

    public int getAge() {
        return age;
    }

    // ### Resource production ###

    public Iterator<Resource> getResources() {
        return resourceList.iterator();
    }
    @Override
    public int get_buying_price() {
        return PRICE;
    }
    @Override
    public int get_selling_price() throws UnauthorizedAction {
        if (this.state == MATURE) {
            return PRICE/2;
        }
        if (this.state == OLD) {
            return PRICE/10;
        }

        if(state == DEAD)
            //Theoretically, it's impossible to enter this part of the code, because a dead animal disappears
            throw new UnauthorizedAction("Impossible to sell a dead animal.");
        else
            throw new UnauthorizedAction("Impossible to sell a BABY farm animal.");
    }

    /** Fonction stopProductionThread qui met fin aux threads de production de chaque ressource */
    public void pauseProductionThread() {
        for (Resource r : resourceList) {
            r.pauseProductionThread();
        }
    }

    public void resumeProductionThread() {
        for (Resource r : resourceList) {
            r.resumeProductionThread();
        }
    }

}