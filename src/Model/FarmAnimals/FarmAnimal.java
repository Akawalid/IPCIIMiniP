package Model.FarmAnimals;

import Model.Entity;
import Model.AgeState;
import Model.Exceptions.UnauthorizedAction;
import Model.Resources.Resource;
import Model.Spot;

import java.util.ArrayList;
import java.util.Iterator;

import static Model.AgeState.*;

public abstract class FarmAnimal extends Entity {

    // ### Resource Selling ### //
    protected final static int PRICE_EWE = 10;
    protected final static int PRICE_SHEEP = 10;
    protected final static int PRICE_HEN = 10;


    /*
    //@Override
    public void move(Point direction) throws InvalidCoordinates{

    @Override
    public void move(Point direction) throws InvalidCoordinates {
        if (this.getPosition() == null) {
            throw new InvalidCoordinates("Position non d�finie pour " + this.getName());
        }
        int newX = this.getPosition().getX() + direction.x;
        int newY = this.getPosition().getY() + direction.y;
        // R�cup�rer la r�f�rence au Farm depuis la position actuelle
        Farm currentFarm = this.getPosition().getFarm();
        // Cr�er un nouveau Spot avec les nouvelles coordonn�es et le farm associ�
        this.setPosition(new Spot(newX, newY, currentFarm));
        System.out.println(this.getName() + " se d�place vers (" + newX + ", " + newY + ")");
    }
    */

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
            //die();
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

    protected abstract int get_price();
    protected int get_buying_price(){
        return get_price();
    }
    protected int get_mature_selling_price(){
        return get_price()/2;
    }

    protected int get_old_selling_price(){
        return get_price()/10;
    }

    public int get_selling_price() throws UnauthorizedAction {
        if (this.state == MATURE) {
            return get_mature_selling_price();
        } else if (this.state == OLD) {
            return get_old_selling_price();
        }
        else {
            throw new UnauthorizedAction("Impossible to sell a BABY farm animal.");
            //ou dead th�oriquement
        }
    }

}