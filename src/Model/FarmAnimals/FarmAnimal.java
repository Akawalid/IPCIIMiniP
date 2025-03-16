package Model;

import Model.Exceptions.InvalidCoordinates;

import java.awt.*;

import static Model.FarmAnimal.AgeState.*;

public abstract class FarmAnimal extends Entity {

    @Override
    abstract public void move(Point direction) throws InvalidCoordinates;


    private int age;       // L'�ge exprim� en nombre de cycles
    private AgeState state;  // "B�b�", "Mature", "Vieux"
    private boolean running; // Pour contr�ler l'ex�cution du thread
    public int prix ;



    public enum AgeState {
        BABY , MATURE, OLD, DEAD;
    }

    public FarmAnimal(String name) {
        super(name); // Initialiser � b�b�
        this.age = 0;
        this.state = BABY;
        this.running = true;

    }


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
