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
}
