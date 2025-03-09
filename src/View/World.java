package View;

import javax.swing.*;
import java.awt.*;

public class World extends JPanel {
    public World(){
        super();
        assert(getParent() != null);
        setBackground(new Color(0x03071e));

        setLayout(new BorderLayout());

        // World Panel (Left Column)
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLUE);
        gamePanel.setBorder(BorderFactory.createTitledBorder("World"));
        add(gamePanel, BorderLayout.CENTER);

        // Control Panel (Right Column)
        JPanel controlPanel = new ControlPanel();
        controlPanel.setBackground(Color.GREEN);
        controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        add(controlPanel, BorderLayout.EAST);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setPreferredSize(getParent().getSize());
    }
}
