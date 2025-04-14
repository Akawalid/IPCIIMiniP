package Model.Entities.FarmAnimals;

import Model.Farm;

/**
 * Simulation thread that updates the model's state.
 * This thread iterates over all the entities in the farm and calls updateAge()
 * on objects of type FarmAnimal at regular intervals.
 */
public class UpdateAgeThread extends Thread {
    // Update delay in milliseconds (5 seconds)
    public static final int UPDATE_DELAY = 5000;
    private Farm farm;

    private boolean active = true;
    private boolean pause = false;

    /**
     * Constructor that initializes the thread with the farm.
     * @param farm the farm containing the entities to be updated.
     */
    public UpdateAgeThread(Farm farm) {
        this.farm = farm;
        farm.setUpdateAgeThread(this);
    }

    public void stopThread(){
        active = false;
    }

    public void pauseThread(){
        pause = true;
    }

    public void resumeThread(){
        pause = false;
    }


    @Override
    public void run() {
        while (active) {
            // Call the synchronized updateEntities method in Farm
            try {
                Thread.sleep(UPDATE_DELAY); // Pause 5s before the next update
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (pause) continue; // Skip the update if paused
            farm.updateEntities();
        }
    }
}