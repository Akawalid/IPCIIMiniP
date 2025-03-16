package Model.FarmAnimals;

import Model.Entity;
import Model.AgeState;
import Model.Exceptions.InvalidCoordinates;
import Model.Resources.Resource;

import java.awt.*;
import java.util.ArrayList;

import static Model.AgeState.*;

public abstract class FarmAnimal extends Entity {

    @Override
    public void move(Point direction) throws InvalidCoordinates{
        //TODO j'ai suppos� que ce n'�tait pas abstract pour pas que �a fasse une erreur
        //dans les classes filles tant que je n'avais pas de r�ponse sur la question suivante :

        //est-ce qu'on souhaite d�finir move ici ?
        //- si oui : alors on doit enlever "abstract" qu'il y avait
        //- si non : on peut enlever la fonction car on n'a pas besoin de l'�crire si
        // on ne la red�finit pas
    }

    //------------------- Attributes -------------------//

    // ### Age evolution ###

    private int age;       // L'�ge exprim� en nombre de cycles
    private AgeState state;  // "B�b�", "Mature", "Vieux"
    private boolean running; // Pour contr�ler l'ex�cution du thread
    public int prix ;

    // ### Resource production ###
    ArrayList<Resource> resourceList;

    //------------------- Methods -------------------//

    /** constructor **/
    public FarmAnimal(String name) {
        //Age evolution
        super(name); // Initialiser � b�b�
        this.age = 0;
        this.state = BABY;
        this.running = true;
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
        if (age>3) {
            state = MATURE;
            // TODO:  Possibilit� d'augmenter la production ou la reproduction � cet �tat
        } else if (age > 7) {
            state = OLD;
            // TODO : R�duire la production et / ou la valeur de revente, par exemple
        } else if (age == 10) {
            // L'animal meurt de vieillesse
            die(); //
            state = DEAD;
        }
        System.out.println("�ge : " + age + " - �tat : " + state);
    }

    // Action � r�aliser lors de la mort (par exemple, retirer l'animal de la simulation)
    private void die() {

        System.out.println("L'animal est mort de vieillesse.");
        running = false;  // Arr�ter le thread
    }

    public Object getState() {
        return state;
    }

    // ### Resource production ###

    public ArrayList<Resource> getResources() {
        return resourceList;
    }

    // ### Buy & Sell ###

    public int sell() {
        if (this.state == BABY) {
            return -1;
        } else if (this.state == MATURE) {
            return 10;
        }
        return 0;
    }

    public int vendre(){
        return this.prix ;
    }


}
