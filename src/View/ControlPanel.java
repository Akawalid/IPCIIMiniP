package View;

import View.Shepherd.ShepherdActionPanel;
import View.Shepherd.ShepherdInformationPanel;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public static final int W = 250, H_INFO_PANEL = 200, H_ACTION_PANEL = 200, MARGIN = 10;
    private JPanel informationPanel;
    private JPanel actionPanel;
    public ControlPanel(){
        super();
        setBackground(new Color(0x03071e));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        informationPanel = new ShepherdInformationPanel();
        actionPanel = new ShepherdActionPanel();
        add(informationPanel);
        add(Box.createRigidArea(new Dimension(0, MARGIN)));
        add(actionPanel);

    }

    @Override
    public void addNotify() {
        super.addNotify();
        assert(getParent() != null);
        setPreferredSize(new Dimension(W, getParent().getHeight()));
    }
}
