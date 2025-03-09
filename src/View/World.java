package View;

import javax.swing.*;
import java.awt.*;

public class World extends JPanel {
    //World is the page that visualizes the core of the game, it contains the land and the control panel.
    public World(){
        super();
        assert(getParent() != null);
        setBackground(new Color(0x03071e));

        setLayout(new BorderLayout());

        // World Panel (Left Column)
        JPanel land = new Land();
        add(land, BorderLayout.CENTER);

        // Control Panel (Right Column)
        JPanel controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.EAST);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setPreferredSize(getParent().getSize());
    }
}
