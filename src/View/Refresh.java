package View;

import javax.swing.*;

public class Refresh extends Thread {
    public static final int DELAY = 100;
    private JComponent toRefresh;
    public Refresh(JComponent toRefresh) {
        this.toRefresh = toRefresh;
    }

    @Override
    public void run() {
        while (true) {
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
