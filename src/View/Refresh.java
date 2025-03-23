package View;

import javax.swing.*;

public class Refresh extends Thread {
    public static final int DELAY = 100;
    private World toRefresh;

    public Refresh(World toRefresh) {
        this.toRefresh = toRefresh;
    }

    @Override
    public void run() {
        while (true) {
            toRefresh.update();
            toRefresh.repaint();
            toRefresh.revalidate();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
