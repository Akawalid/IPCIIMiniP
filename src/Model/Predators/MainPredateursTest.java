//package Model.Predators;
//
//import static org.junit.Assert.*;
//import org.junit.Test;
//import Model.Farm;
//import Model.AgeState;
//import Model.Exceptions.InvalidCoordinates;
//import Model.FarmAnimals.Sheep;
//import Model.Predators.Wolf;
//import java.awt.Point;
//
//public class MainPredateursTest {
//
//    /**
//     * Ce test v�rifie que lorsqu'un pr�dateur attaque une proie,
//     * l'�ge de la proie est forc� � 10 et son �tat passe � DEAD.
//     */
//    @Test
//    public void testAttackSetsAgeTo10AndKillsPrey() {
//        // Cr�ation d'une ferme pour r�cup�rer des spots
//        Farm farm = new Farm();
//
//        // Cr�ation d'une brebis avec un �ge initial inf�rieur � 10
//        Sheep sheep = new Sheep("TestSheep");
//        sheep.setPosition(farm.getSpot(0, 0));
//        sheep.setAge(5);  // On simule un �ge initial de 5
//
//        // Cr�ation d'un loup qui attaquera la brebis
//        Wolf wolf = new Wolf("TestWolf");
//        wolf.setPosition(farm.getSpot(1, 1));
//
//        // Le loup attaque la brebis
//        wolf.attack(sheep);
//
//        // V�rification que l'�ge de la brebis est pass� � 10
//        assertEquals(10, sheep.getAge());
//        // V�rification que l'�tat de la brebis est pass� � DEAD
//        assertEquals(AgeState.DEAD, sheep.getState());
//    }
//
//    /**
//     * Ce test v�rifie que la m�thode move met bien � jour la position d'une entit�.
//     */
//    @Test
//    public void testMoveMethod() throws InvalidCoordinates {
//        Farm farm = new Farm();
//        Sheep sheep = new Sheep("TestSheep");
//        sheep.setPosition(farm.getSpot(2, 2));
//
//        // Définition d'une direction de d�placement
//        Point moveDirection = new Point(3, 4);
//        sheep.move(moveDirection);
//
//        // La nouvelle position attendue est (2+3, 2+4) = (5, 6)
//        assertEquals(5, sheep.getPosition().getX());
//        assertEquals(6, sheep.getPosition().getY());
//    }
//}
