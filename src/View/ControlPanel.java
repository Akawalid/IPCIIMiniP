package View;

import View.Shepherd.ShepherdActionPanel;
import View.Shepherd.ShepherdInformationPanel;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    //This class represents the panel that comes on the left side of the screen, it will contain the entities information
    // and actions.

    //MARGIN, represents the margin between the information, actions panels and the whole ControlPanel
    public static final int W = 250, H_INFO_PANEL = 200, H_ACTION_PANEL = 200, MARGIN = 10;
    //first part of the panel is the information panel, on top
    private JPanel informationPanel;
    //second part of the panel is the action panel, on the bottom
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

    //The dimensions of the current container depends on the parent container's dimensions.
    //This methode executes once the current component is added to parent container.
    @Override
    public void addNotify() {
        super.addNotify();
        assert(getParent() != null);
        setPreferredSize(new Dimension(W, getParent().getHeight()));
    }
}
