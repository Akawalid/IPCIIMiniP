package Model.FarmAnimals.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import Model.FarmAnimals.Sheep;
import Model.AgeState;

/**
 * Test class to verify the evolution of age and state of a farm animal.
 */
public class FarmAnimalAgeTest {

    /**
     * Test the evolution of age and state of a sheep.
     */
    @Test
    public void testAgeEvolution() {
        // Creation of a sheep for testing
        Sheep sheep = new Sheep("TestSheep");

        // Verify the initial state: age 0 and state BABY
        assertEquals(0, sheep.getAge(), "Initial age should be 0");
        assertEquals(AgeState.BABY, sheep.getState(), "Initial state should be BABY");

        // Call updateAge() three times: the animal goes from 0 to 3 cycles and should remain BABY
        for (int i = 0; i < 3; i++) {
            sheep.updateAge();
        }
        assertEquals(3, sheep.getAge(), "After 3 updates, age should be 3");
        assertEquals(AgeState.BABY, sheep.getState(), "At age 3, state should remain BABY");

        // 4th cycle: age becomes 4 -> 4 > 3 and 4 < 7 so state becomes MATURE
        sheep.updateAge();
        assertEquals(4, sheep.getAge(), "After 4 updates, age should be 4");
        assertEquals(AgeState.MATURE, sheep.getState(), "At age 4, state should be MATURE");

        // 5th cycle: age becomes 5 -> still between 3 and 7, state remains MATURE
        sheep.updateAge();
        assertEquals(5, sheep.getAge(), "After 5 updates, age should be 5");
        assertEquals(AgeState.MATURE, sheep.getState(), "At age 5, state should be MATURE");

        // 6th cycle: age becomes 6 -> still between 3 and 7, state remains MATURE
        sheep.updateAge();
        assertEquals(6, sheep.getAge(), "After 6 updates, age should be 6");
        assertEquals(AgeState.MATURE, sheep.getState(), "At age 6, state should be MATURE");

        // 7th cycle: age becomes 7 -> no condition is met, state remains as before (MATURE)
        sheep.updateAge();
        assertEquals(7, sheep.getAge(), "After 7 updates, age should be 7");
        assertEquals(AgeState.MATURE, sheep.getState(), "At age 7, state should remain MATURE");

        // 8th cycle: age becomes 8 -> 8 > 7 and 8 < 10 so state becomes OLD
        sheep.updateAge();
        assertEquals(8, sheep.getAge(), "After 8 updates, age should be 8");
        assertEquals(AgeState.OLD, sheep.getState(), "At age 8, state should be OLD");

        // 9th cycle: age becomes 9 -> still between 7 and 10, state remains OLD
        sheep.updateAge();
        assertEquals(9, sheep.getAge(), "After 9 updates, age should be 9");
        assertEquals(AgeState.OLD, sheep.getState(), "At age 9, state should be OLD");

        // 10th cycle: age becomes 10 -> the animal dies and state becomes DEAD
        sheep.updateAge();
        assertEquals(10, sheep.getAge(), "After 10 updates, age should be 10");
        assertEquals(AgeState.DEAD, sheep.getState(), "At age 10, state should be DEAD");
    }
}
