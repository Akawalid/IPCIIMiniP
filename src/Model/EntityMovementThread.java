package Model;

import Model.Exceptions.InvalidCoordinates;
import Model.Shepherd.Shepherd;

public class EntityMovementThread extends Thread {
    public static final int DELAY = 1000;
    private Entity e;
    public EntityMovementThread(Entity e){
        this.e = e;
    }
    @Override
    public void run() {
        while (!e.getPath().isEmpty()) {
            try {
                e.move();
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InvalidCoordinates e) {
                //We have to display for the ser an error invalid coordinates
                System.out.print("Invalid coordinates: ShepherdMovementThread, line: 21");
            }
        }
    }
}
