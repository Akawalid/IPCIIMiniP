package Model.Entities;

import Model.Farm;

public class CleanDeadEntitiesThread extends Thread {
    private static final int DELAY = 100;
    private Farm farm;

    public CleanDeadEntitiesThread(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void run() {
        while (true) {
            // Call the synchronized cleanDeadEntities method in Farm
            farm.cleanDeadEntities();
            try {
                Thread.sleep(DELAY); // Pause 5s before the next update
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
