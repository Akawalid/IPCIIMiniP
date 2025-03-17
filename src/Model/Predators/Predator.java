// Fichier : Model/Predators/Predator.java
package Model.Predators;

import Model.Entity;
import Model.Exceptions.InvalidCoordinates;
import Model.Farm;
import Model.FarmAnimals.FarmAnimal;
import Model.Spot;
import java.awt.*;

public abstract class Predator extends Entity {
    protected int aggressivity;
    protected int patience;   // Temps avant de retourner à son terrier
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

    // Méthode d'attaque sur une proie
    public void attack(FarmAnimal prey) {
        System.out.println(this.getName() + " attaque " + prey.getName());
        // Forcer l'âge de la proie à 10 pour déclencher la mort (via setAge)
        prey.setAge(10);
        System.out.println(prey.getName() + " a été tué par " + this.getName());
    }


    @Override
    public void move(Point direction) throws InvalidCoordinates {
        if (this.getPosition() == null) {
            throw new InvalidCoordinates("Position non définie pour " + this.getName());
        }
        int newX = this.getPosition().getX() + direction.x;
        int newY = this.getPosition().getY() + direction.y;
        // Récupérer la référence au Farm depuis la position actuelle
        Farm currentFarm = this.getPosition().getFarm();
        // Créer un nouveau Spot avec les nouvelles coordonnées et le farm
        this.setPosition(new Spot(newX, newY, currentFarm));
        System.out.println(this.getName() + " se déplace vers (" + newX + ", " + newY + ")");
    }



    // Méthode de fuite lorsque le prédateur aperçoit un jardinier
    public void flee() {
        System.out.println(this.getName() + " aperçoit le jardinier et s'enfuit !");
        this.setPosition(null); // On peut considérer que le prédateur quitte la zone de jeu
        fleeing = true;
    }
}
