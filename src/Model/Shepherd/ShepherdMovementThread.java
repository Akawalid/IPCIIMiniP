package Model.Shepherd;

import Model.Exceptions.InvalidCoordinates;

public class ShepherdMovementThread extends Thread {
    public static final int DELAY = 1000;
    private Shepherd s;
    public ShepherdMovementThread(Shepherd s){
        this.s = s;
    }
    @Override
    public void run() {
        while (true) {
            try {
                s.move();
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
