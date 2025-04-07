package Model.FarmAnimals;

import Model.Bank;
import Model.Entity;
import Model.AgeState;
import Model.Exceptions.UnauthorizedAction;
//import Model.Predators.FoxDen;
import Model.Resources.Resource;
import Model.Spot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static Model.AgeState.*;

public abstract class FarmAnimal extends Entity {

    //------------------- Attributes -------------------//

    // ### Age evolution ###

    private int age;       // L'âge exprimé en nombre de cycles
    private AgeState state;  // "Bébé", "Mature", "Vieux"

    // ### Resource production ###
    ArrayList<Resource> resourceList;

    //------------------- Methods -------------------//

    /** constructor **/
    public FarmAnimal(Spot s) {
        super(s);
        //Age evolution
        // Initialiser à bébé
        this.age = 0;
        this.state = BABY;
        //Resource production
        resourceList = new ArrayList<>();
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

    // ### Buy & Sell ###
    @Override
    public int get_buying_price(){
        return Bank.get_price(this);
    }

    @Override
    public int get_selling_price() throws UnauthorizedAction {
        int price = Bank.get_price(this);
        if (this.state == MATURE) {
            return price/2;
        } else if (this.state == OLD) {
            return price/10;
        }
        else {
            throw new UnauthorizedAction("Impossible to sell a BABY farm animal.");
            //ou dead théoriquement
        }
    }


    // Ajoutez cette méthode pour supprimer une entité de la ferme
    /*public void removeEntity(Entity e) {
        creatures.remove(e);
    }*/







}