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

    private int age;       // L'�ge exprim� en nombre de cycles
    private AgeState state;  // "B�b�", "Mature", "Vieux"

    // ### Resource production ###
    ArrayList<Resource> resourceList;

    //------------------- Methods -------------------//

    /** constructor **/
    public FarmAnimal(Spot s) {
        super(s);
        //Age evolution
        // Initialiser � b�b�
        this.age = 0;
        this.state = BABY;
        //Resource production
        resourceList = new ArrayList<>();
    }

    // ### Age evolution ###

    public final void updateAge() {
        // Si l'animal est d�j� mort, on ne fait rien
        if (state == DEAD) {
            return;
        }
        age++;
        if (age>3 && age<7) {
            state = MATURE;
            // TODO:  Possibilit� d'augmenter la production ou la reproduction � cet �tat
        } else if (age > 7 && age < 10) {
            state = OLD;
            // TODO : R�duire la production et / ou la valeur de revente, par exemple
        } else if (age == 10) {
            // L'animal meurt de vieillesse
            System.out.println("L'animal est mort de vieillesse.");
            state = DEAD;
        }
        System.out.println("�ge : " + age + " - �tat : " + state);
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
            //ou dead th�oriquement
        }
    }


    // Ajoutez cette m�thode pour supprimer une entit� de la ferme
    /*public void removeEntity(Entity e) {
        creatures.remove(e);
    }*/







}