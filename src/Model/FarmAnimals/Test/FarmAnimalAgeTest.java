package Model.FarmAnimals.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import Model.FarmAnimals.Sheep;
import Model.AgeState;

public class FarmAnimalAgeTest {

    @Test
    public void testAgeEvolution() {
        // Cr�ation d'un mouton pour le test
        Sheep sheep = new Sheep("TestSheep");

        // V�rification de l'�tat initial
        assertEquals(0, sheep.getAge(), "L'�ge initial devrait �tre 0");
        assertEquals(AgeState.BABY, sheep.getState(), "L'�tat initial devrait �tre BABY");

        // Appeler updateAge() trois fois : l'animal passe de 0 � 3 cycles et doit rester BABY
        for (int i = 0; i < 3; i++) {
            sheep.updateAge();
        }
        assertEquals(3, sheep.getAge(), "Apr�s 3 mises � jour, l'�ge devrait �tre 3");
        assertEquals(AgeState.BABY, sheep.getState(), "� l'�ge 3, l'�tat devrait rester BABY");

        // 4�me cycle : age passe � 4 -> 4 > 3 et 4 < 7 donc �tat devient MATURE
        sheep.updateAge();
        assertEquals(4, sheep.getAge(), "Apr�s 4 mises � jour, l'�ge devrait �tre 4");
        assertEquals(AgeState.MATURE, sheep.getState(), "� l'�ge 4, l'�tat devrait �tre MATURE");

        // 5�me cycle : age passe � 5 -> toujours entre 3 et 7, �tat reste MATURE
        sheep.updateAge();
        assertEquals(5, sheep.getAge(), "Apr�s 5 mises � jour, l'�ge devrait �tre 5");
        assertEquals(AgeState.MATURE, sheep.getState(), "� l'�ge 5, l'�tat devrait �tre MATURE");

        // 6�me cycle : age passe � 6 -> toujours entre 3 et 7, �tat reste MATURE
        sheep.updateAge();
        assertEquals(6, sheep.getAge(), "Apr�s 6 mises � jour, l'�ge devrait �tre 6");
        assertEquals(AgeState.MATURE, sheep.getState(), "� l'�ge 6, l'�tat devrait �tre MATURE");

        // 7�me cycle : age passe � 7 -> aucune condition n'est satisfaite, l'�tat reste celui d'avant (MATURE)
        sheep.updateAge();
        assertEquals(7, sheep.getAge(), "Apr�s 7 mises � jour, l'�ge devrait �tre 7");
        assertEquals(AgeState.MATURE, sheep.getState(), "� l'�ge 7, l'�tat devrait rester MATURE");

        // 8�me cycle : age passe � 8 -> 8 > 7 et 8 < 10 donc �tat devient OLD
        sheep.updateAge();
        assertEquals(8, sheep.getAge(), "Apr�s 8 mises � jour, l'�ge devrait �tre 8");
        assertEquals(AgeState.OLD, sheep.getState(), "� l'�ge 8, l'�tat devrait �tre OLD");

        // 9�me cycle : age passe � 9 -> toujours entre 7 et 10, �tat reste OLD
        sheep.updateAge();
        assertEquals(9, sheep.getAge(), "Apr�s 9 mises � jour, l'�ge devrait �tre 9");
        assertEquals(AgeState.OLD, sheep.getState(), "� l'�ge 9, l'�tat devrait �tre OLD");

        // 10�me cycle : age passe � 10 -> l'animal meurt et l'�tat devient DEAD
        sheep.updateAge();
        assertEquals(10, sheep.getAge(), "Apr�s 10 mises � jour, l'�ge devrait �tre 10");
        assertEquals(AgeState.DEAD, sheep.getState(), "� l'�ge 10, l'�tat devrait �tre DEAD");
    }
}
