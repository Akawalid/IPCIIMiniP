package Model.Predators;

import static org.junit.Assert.*;
import org.junit.Test;
import Model.Farm;
import Model.AgeState;
import Model.Exceptions.InvalidCoordinates;
import Model.FarmAnimals.Sheep;
import Model.Predators.Wolf;
import java.awt.Point;

public class MainPredateursTest {

    /**
     * Ce test vérifie que lorsqu'un prédateur attaque une proie,
     * l'âge de la proie est forcé à 10 et son état passe à DEAD.
     */
    @Test
    public void testAttackSetsAgeTo10AndKillsPrey() {
        // Création d'une ferme pour récupérer des spots
        Farm farm = new Farm();

        // Création d'une brebis avec un âge initial inférieur à 10
        Sheep sheep = new Sheep("TestSheep");
        sheep.setPosition(farm.getSpot(0, 0));
        sheep.setAge(5);  // On simule un âge initial de 5

        // Création d'un loup qui attaquera la brebis
        Wolf wolf = new Wolf("TestWolf");
        wolf.setPosition(farm.getSpot(1, 1));

        // Le loup attaque la brebis
        wolf.attack(sheep);

        // Vérification que l'âge de la brebis est passé à 10
        assertEquals(10, sheep.getAge());
        // Vérification que l'état de la brebis est passé à DEAD
        assertEquals(AgeState.DEAD, sheep.getState());
    }

    /**
     * Ce test vérifie que la méthode move met bien à jour la position d'une entité.
     */
    @Test
    public void testMoveMethod() throws InvalidCoordinates {
        Farm farm = new Farm();
        Sheep sheep = new Sheep("TestSheep");
        sheep.setPosition(farm.getSpot(2, 2));

        // Définition d'une direction de déplacement
        Point moveDirection = new Point(3, 4);
        sheep.move(moveDirection);

        // La nouvelle position attendue est (2+3, 2+4) = (5, 6)
        assertEquals(5, sheep.getPosition().getX());
        assertEquals(6, sheep.getPosition().getY());
    }
}
