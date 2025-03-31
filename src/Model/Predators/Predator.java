// Fichier : Model/Predators/Predator.java
/*package Model.Predators;

import Model.Entity;
import Model.Exceptions.InvalidCoordinates;
import Model.Farm;
import Model.FarmAnimals.FarmAnimal;
import Model.Spot;
import java.awt.*;

public abstract class Predator extends Entity {
    protected int aggressivity;
    protected int patience;   // Temps avant de retourner dans son terrier
    protected int speed;      // La vitesse dépend de l'agressivité
    protected boolean fleeing = false;

    public Predator(String name) {
        super(name);
    }

    public Predator(Spot position, String name) {
        super(position, name);
    }

    // Chaque prédateur vise une espèce spécifique d'élevage
    public abstract Class<? extends FarmAnimal> getTargetPreyClass();

    // M�thode d'attaque sur une proie
    public void attack(FarmAnimal prey) {
        System.out.println(this.getId() + " attaque " + prey.getId());
        // Forcer l'�ge de la proie � 10 pour d�clencher la mort (via setAge)
        //prey.setAge(10);
        System.out.println(prey.getId() + " a �t� tu� par " + this.getId());
    }


/*    @Override
    public void move(Point direction) throws InvalidCoordinates {
        if (this.getPosition() == null) {
            throw new InvalidCoordinates("Position non d�finie pour " + this.getName());
        }
        int newX = this.getPosition().getX() + direction.x;
        int newY = this.getPosition().getY() + direction.y;
        // R�cup�rer la r�f�rence au Farm depuis la position actuelle
        Farm currentFarm = this.getPosition().getFarm();
        // Cr�er un nouveau Spot avec les nouvelles coordonn�es et le farm
        this.setPosition(new Spot(newX, newY, currentFarm));
        System.out.println(this.getName() + " se d�place vers (" + newX + ", " + newY + ")");
    }
*/


    // Méthode de fuite lorsque le prédateur aperçoit un jardinier

//}