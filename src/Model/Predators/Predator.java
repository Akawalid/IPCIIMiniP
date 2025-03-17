//// Fichier : Model/Predators/Predator.java
//package Model.Predators;
//
//import Model.Entity;
//import Model.Exceptions.InvalidCoordinates;
//import Model.Farm;
//import Model.FarmAnimals.FarmAnimal;
//import Model.Spot;
//import java.awt.*;
//
//public abstract class Predator extends Entity {
//    protected int aggressivity;
//    protected int patience;   // Temps avant de retourner � son terrier
//    protected int speed;      // La vitesse d�pend de l'agressivit�
//    protected boolean fleeing = false;
//
//    public Predator(String name) {
//        super(name);
//    }
//
//    public Predator(Spot position, String name) {
//        super(position, name);
//    }
//
//    // Chaque pr�dateur vise une esp�ce sp�cifique d'�levage
//    public abstract Class<? extends FarmAnimal> getTargetPreyClass();
//
//    // M�thode d'attaque sur une proie
//    public void attack(FarmAnimal prey) {
//        System.out.println(this.getName() + " attaque " + prey.getName());
//        // Forcer l'�ge de la proie � 10 pour d�clencher la mort (via setAge)
//        prey.setAge(10);
//        System.out.println(prey.getName() + " a �t� tu� par " + this.getName());
//    }
//
//
//    @Override
//    public void move(Point direction) throws InvalidCoordinates {
//        if (this.getPosition() == null) {
//            throw new InvalidCoordinates("Position non d�finie pour " + this.getName());
//        }
//        int newX = this.getPosition().getX() + direction.x;
//        int newY = this.getPosition().getY() + direction.y;
//        // R�cup�rer la r�f�rence au Farm depuis la position actuelle
//        Farm currentFarm = this.getPosition().getFarm();
//        // Cr�er un nouveau Spot avec les nouvelles coordonn�es et le farm
//        this.setPosition(new Spot(newX, newY, currentFarm));
//        System.out.println(this.getName() + " se d�place vers (" + newX + ", " + newY + ")");
//    }
//
//
//
//    // M�thode de fuite lorsque le pr�dateur aper�oit un jardinier
//    public void flee() {
//        System.out.println(this.getName() + " aper�oit le jardinier et s'enfuit !");
//        this.setPosition(null); // On peut consid�rer que le pr�dateur quitte la zone de jeu
//        fleeing = true;
//    }
//}
