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
     * Test the evolution of age and state of an animal for example a sheep.
     */
    @Test
    public void testAgeEvolutionShort() {
        // Create a sheep for testing
        Sheep sheep = new Sheep("TestSheep");

        // Verify initial conditions: age 0 and state BABY
        assertEquals(0, sheep.getAge(), "Initial age should be 0");
        assertEquals(AgeState.BABY, sheep.getState(), "Initial state should be BABY");

        // For each update cycle, verify the expected age and state
        for (int i = 1; i <= 10; i++) {
            sheep.updateAge();
            int expectedAge = i;
            // Determine the expected state based on the current cycle:
            // Cycles 1 to 3: BABY, 4 to 7: MATURE, 8-9: OLD, 10: DEAD.
            AgeState expectedState;
            if (i <= 3) {
                expectedState = AgeState.BABY;
            } else if (i < 8) {
                expectedState = AgeState.MATURE;
            } else if (i < 10) {
                expectedState = AgeState.OLD;
            } else {
                expectedState = AgeState.DEAD;
            }

            assertEquals(expectedAge, sheep.getAge(), "At cycle " + i + ", age should be " + expectedAge);
            assertEquals(expectedState, sheep.getState(), "At cycle " + i + ", state should be " + expectedState);
        }
    }
}
