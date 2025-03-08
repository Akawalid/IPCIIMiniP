package View;

import javax.swing.*;
import java.awt.*;

public class World extends JPanel {
    public World(){
        super();
        assert(getParent() != null);
        setPreferredSize(getParent().getSize());
        setBackground(new Color(0x03071e));

        setLayout(new GridLayout(1, 2));

        // World Panel (Left Column)
        JPanel worldPanel = new JPanel();
        worldPanel.setBackground(Color.BLUE);
        worldPanel.setBorder(BorderFactory.createTitledBorder("World"));
        add(worldPanel);

        // Control Panel (Right Column)
        JPanel controlPanel = new FarmerPanel();
        controlPanel.setBackground(Color.GREEN);
        controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        add(controlPanel);
    }
}
