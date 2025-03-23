package View.ControlPanelComponents.Action;

import javax.swing.*;
import Controller.Controller;

public abstract class ActionPanel extends JPanel {
    protected static final int MARGIN = 5;
    protected Controller controller;

    // Default constructor
    public ActionPanel() {
        // Basic initialization of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
    }

    /**
     * Updates the state of the action panel.
     * This method must be implemented by derived classes.
     */
    public abstract void update();

    /**
     * Connects the action panel to the controller.
     * @param c The controller to connect.
     */
    public void connect(Controller c) {
        this.controller = c;
        // Additional logic to connect the panel to the controller
    }

    /**
     * Resets the action panel.
     * This method can be used to reset the state of the panel.
     */
    public void reset() {
        removeAll();
        revalidate();
        repaint();
    }
}
