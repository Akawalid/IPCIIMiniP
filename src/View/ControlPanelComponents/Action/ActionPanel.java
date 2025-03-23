package View.ControlPanelComponents.Action;

import javax.swing.*;

import Controller.Controller;

public abstract class ActionPanel extends JPanel {
    protected static final int MARGIN = 5;

    public abstract void update();
    public abstract void connect(Controller c);

}
