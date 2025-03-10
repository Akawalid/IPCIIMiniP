package Model;

import Model.Exceptions.InvalidCoordinates;

import java.awt.*;

import static Model.FarmAnimal.AgeState.*;

public abstract class FarmAnimal extends Entity {

    @Override
    abstract public void move(Point direction) throws InvalidCoordinates;


    private int age;       // L'âge exprimé en nombre de cycles
    private AgeState state;  // "Bébé", "Mature", "Vieux"
    private boolean running; // Pour contrôler l'exécution du thread
    public int prix ;



    public enum AgeState {
        BABY , MATURE, OLD, DEAD;
    }

    public FarmAnimal(String name) {
        super(name); // Initialiser à bébé
        this.age = 0;
        this.state = BABY;
        this.running = true;

    }


    public final void updateAge() {
        // Si l'animal est déjà mort, on ne fait rien
        if (state == DEAD) {
            return;
        }
        age++;
        if (age>3) {
            state = MATURE;
            // TODO:  Possibilité d'augmenter la production ou la reproduction à cet état
        } else if (age > 7) {
            state = OLD;
            // TODO : Réduire la production et / ou la valeur de revente, par exemple
        } else if (age == 10) {
            // L'animal meurt de vieillesse
            die(); //
            state = DEAD;
        }
        System.out.println("Âge : " + age + " - État : " + state);
    }

    // Action à réaliser lors de la mort (par exemple, retirer l'animal de la simulation)
    private void die() {

        System.out.println("L'animal est mort de vieillesse.");
        running = false;  // Arrêter le thread
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
