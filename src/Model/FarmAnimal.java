package Model;

import Model.Exceptions.InvalidCoordinates;

import java.awt.*;

public class FarmAnimal extends Entity {

    @Override
    public void move(Point direction) throws InvalidCoordinates {

    }





    private int age;       // L'âge exprimé en nombre de cycles
    private String state;  // "Bébé", "Mature", "Vieux"
    private static final int MATURITY_THRESHOLD = 3; // Animal Mature (au 3e cycle)
    private static final int OLD_THRESHOLD = 7;      // Animal Vieux
    private static final int DEATH_AGE = 10;         // Animal meurt de vieillesse
    private boolean running; // Pour contrôler l'exécution du thread
    // private EntityManager entitymanager; // Référence à la simulation

    public FarmAnimal(String name) {
        super(name); // Initialiser à bébé
        this.age = 0;
        this.state = "Bébé";
        this.running = true;

    }


    public void updateAge() {
        // Si l'animal est déjà mort, on ne fait rien
        if (state.equals("Mort")) {
            return;
        }
        age++;
        if (age < MATURITY_THRESHOLD) {
            state = "Bébé";
        } else if (age < OLD_THRESHOLD) {
            state = "Mature";
            // TODO:  Possibilité d'augmenter la production ou la reproduction à cet état
        } else if (age < DEATH_AGE) {
            state = "Vieux";
            // TODO : Réduire la production et / ou la valeur de revente, par exemple
        } else {
            // L'animal meurt de vieillesse
            die(); //
            state = "Mort";
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

    public static void main (String [] args){
        FarmAnimal fa = new FarmAnimal("John");
        fa.updateAge();
    }
}
