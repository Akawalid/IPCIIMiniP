package Model.FarmAnimals;

import Model.Entity;
import Model.AgeState;
import Model.Exceptions.InvalidCoordinates;
import Model.Farm;
import Model.Resources.Resource;
import Model.Spot;

import java.awt.*;
import java.util.ArrayList;

import static Model.AgeState.*;

public abstract class FarmAnimal extends Entity {

    /*@Override
    public void move(Point direction) throws InvalidCoordinates{
        //TODO j'ai supposé que ce n'était pas abstract pour pas que ça fasse une erreur
        //dans les classes filles tant que je n'avais pas de réponse sur la question suivante :

        //est-ce qu'on souhaite définir move ici ?
        //- si oui : alors on doit enlever "abstract" qu'il y avait
        //- si non : on peut enlever la fonction car on n'a pas besoin de l'écrire si
        // on ne la redéfinit pas
    }*/


    @Override
    public void move(Point direction) throws InvalidCoordinates {
        if (this.getPosition() == null) {
            throw new InvalidCoordinates("Position non définie pour " + this.getName());
        }
        int newX = this.getPosition().getX() + direction.x;
        int newY = this.getPosition().getY() + direction.y;
        // Récupérer la référence au Farm depuis la position actuelle
        Farm currentFarm = this.getPosition().getFarm();
        // Créer un nouveau Spot avec les nouvelles coordonnées et le farm associé
        this.setPosition(new Spot(newX, newY, currentFarm));
        System.out.println(this.getName() + " se déplace vers (" + newX + ", " + newY + ")");
    }


    //------------------- Attributes -------------------//

    // ### Age evolution ###

    private int age;       // L'âge exprimé en nombre de cycles
    private AgeState state;  // "Bébé", "Mature", "Vieux"
    private boolean running; // Pour contrôler l'exécution du thread
    public int prix ;

    // ### Resource production ###
    ArrayList<Resource> resourceList;

    //------------------- Methods -------------------//

    /** constructor **/
    public FarmAnimal(String name) {
        //Age evolution
        super(name); // Initialiser à bébé
        this.age = 0;
        this.state = BABY;
        this.running = true;
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
        if (age>3) {
            state = MATURE;
            // TODO:  Possibilité d'augmenter la production ou la reproduction à cet état
        } else if (age > 7) {
            state = OLD;
            // TODO : Réduire la production et / ou la valeur de revente, par exemple
        } else if (age >= 10) {
            // L'animal meurt de vieillesse

            die(); //
            System.out.println("L'animal est mort de vieillesse.");
            state = DEAD;
        }
        System.out.println("Âge : " + age + " - État : " + state);
    }

    // Action à réaliser lors de la mort (par exemple, retirer l'animal de la simulation)
    private void die() {

        //
        running = false;  // Arrêter le thread
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

    public void setAge(int newAge) {
        this.age = newAge;
        // Si l'âge atteint 10 ou plus, l'animal meurt de vieillesse
        if (this.age >= 10 && this.state != AgeState.DEAD) {
            die();
            this.state = AgeState.DEAD;
        }
        System.out.println("Âge mis à jour : " + age + " - État : " + state);
    }

    public Object getAge() {
        return age;
    }


    /*public void decreaseHealth(int damage) {
    }*/
}
