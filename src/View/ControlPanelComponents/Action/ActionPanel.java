package View.ControlPanelComponents.Action;

import javax.swing.*;
import Controller.Controller;

public abstract class ActionPanel extends JPanel {
    protected static final int MARGIN = 5;

    // Default constructor
    public ActionPanel() {
        // Basic initialization of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
    }

    /**
     * connects te content of the panel with controller, likewise, buttons and TextLabels.
     */
    public abstract void connect(Controller c);

    /**
     * Updates the state of the action panel.
     * This method must be implemented by derived classes.
     */
    public abstract void update();

    /**
     * Resets the action panel.
     * This method can be used to reset the state of the panel.
     */
    public void reset() {
        removeAll();
        //alors attention, le contenu du panel ne doit pas être enlevé,
        //sinon il faut changer des trucs
        revalidate();
        repaint();
    }
}
