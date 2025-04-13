package View;

import View.World.World;

import javax.swing.*;

public class Refresh extends Thread {
    public static final int DELAY = 100;
    private final PrincipalPanel toRefresh;
    private volatile boolean running = true;

    public Refresh(PrincipalPanel toRefresh) {
        this.toRefresh = toRefresh;
    }

    @Override
    public void run() {
        while (running) {
            /*
            Swing has an invariant that has to be maintained:
                Invariant: All Swing components must be accessed/modified only on the Event Dispatch Thread (EDT).
             Schedule repaint on EDT

             for your information, EDT is the main thread of swing, it contains a queue of events that works as
             the following:
                User Action->>EDT: Mouse click/keypress
                EDT->>Your Code: Invokes listeners
                Your Code->>EDT: Calls repaint()
                EDT->>Component: Processes paint events
             */
            SwingUtilities.invokeLater(() -> {
                toRefresh.repaint();
                toRefresh.revalidate();
            });

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt flag
                break;
            }
        }
    }

    public void stopRefresh() {
        running = false;
        this.interrupt();
    }
}