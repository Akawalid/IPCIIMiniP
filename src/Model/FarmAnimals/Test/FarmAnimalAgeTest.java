package Model.FarmAnimals.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import Model.FarmAnimals.Sheep;
import Model.AgeState;

public class FarmAnimalAgeTest {

    @Test
    public void testAgeEvolution() {
        // Création d'un mouton pour le test
        Sheep sheep = new Sheep("TestSheep");

        // Vérification de l'état initial
        assertEquals(0, sheep.getAge(), "L'âge initial devrait être 0");
        assertEquals(AgeState.BABY, sheep.getState(), "L'état initial devrait être BABY");

        // Appeler updateAge() trois fois : l'animal passe de 0 à 3 cycles et doit rester BABY
        for (int i = 0; i < 3; i++) {
            sheep.updateAge();
        }
        assertEquals(3, sheep.getAge(), "Après 3 mises à jour, l'âge devrait être 3");
        assertEquals(AgeState.BABY, sheep.getState(), "À l'âge 3, l'état devrait rester BABY");

        // 4ème cycle : age passe à 4 -> 4 > 3 et 4 < 7 donc état devient MATURE
        sheep.updateAge();
        assertEquals(4, sheep.getAge(), "Après 4 mises à jour, l'âge devrait être 4");
        assertEquals(AgeState.MATURE, sheep.getState(), "À l'âge 4, l'état devrait être MATURE");

        // 5ème cycle : age passe à 5 -> toujours entre 3 et 7, état reste MATURE
        sheep.updateAge();
        assertEquals(5, sheep.getAge(), "Après 5 mises à jour, l'âge devrait être 5");
        assertEquals(AgeState.MATURE, sheep.getState(), "À l'âge 5, l'état devrait être MATURE");

        // 6ème cycle : age passe à 6 -> toujours entre 3 et 7, état reste MATURE
        sheep.updateAge();
        assertEquals(6, sheep.getAge(), "Après 6 mises à jour, l'âge devrait être 6");
        assertEquals(AgeState.MATURE, sheep.getState(), "À l'âge 6, l'état devrait être MATURE");

        // 7ème cycle : age passe à 7 -> aucune condition n'est satisfaite, l'état reste celui d'avant (MATURE)
        sheep.updateAge();
        assertEquals(7, sheep.getAge(), "Après 7 mises à jour, l'âge devrait être 7");
        assertEquals(AgeState.MATURE, sheep.getState(), "À l'âge 7, l'état devrait rester MATURE");

        // 8ème cycle : age passe à 8 -> 8 > 7 et 8 < 10 donc état devient OLD
        sheep.updateAge();
        assertEquals(8, sheep.getAge(), "Après 8 mises à jour, l'âge devrait être 8");
        assertEquals(AgeState.OLD, sheep.getState(), "À l'âge 8, l'état devrait être OLD");

        // 9ème cycle : age passe à 9 -> toujours entre 7 et 10, état reste OLD
        sheep.updateAge();
        assertEquals(9, sheep.getAge(), "Après 9 mises à jour, l'âge devrait être 9");
        assertEquals(AgeState.OLD, sheep.getState(), "À l'âge 9, l'état devrait être OLD");

        // 10ème cycle : age passe à 10 -> l'animal meurt et l'état devient DEAD
        sheep.updateAge();
        assertEquals(10, sheep.getAge(), "Après 10 mises à jour, l'âge devrait être 10");
        assertEquals(AgeState.DEAD, sheep.getState(), "À l'âge 10, l'état devrait être DEAD");
    }
}
