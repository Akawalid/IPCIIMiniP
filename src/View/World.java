package View;

import Model.Farm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class World extends JLayeredPane {
    //World is the page that visualizes the core of the game, it contains the land and the control panel.
    private Farm farm;
    private JPanel shadowPanel;
    private JPanel storePanel;
    private JPanel worldPanel;

    public World(Farm farm){
        super();

        this.farm = farm;

        worldPanel = new JPanel();
        worldPanel.setLayout(new BorderLayout());

        // World Panel (Left Column)
        JPanel land = new Land(farm);
        worldPanel.add(land, BorderLayout.CENTER);

        // Control Panel (Right Column)
        JPanel controlPanel = new ControlPanel(farm);
        worldPanel.add(controlPanel, BorderLayout.EAST);

        add(worldPanel, JLayeredPane.DEFAULT_LAYER); // Default layer


        shadowPanel = new JPanel();
        shadowPanel.setBackground(new Color(0, 0, 0, 150));


        storePanel = new StorePanel();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBounds();
            }
        });
    }

    public void updateBounds() {
        worldPanel.setBounds(0, 0, getWidth(), getHeight());
        shadowPanel.setBounds(0, 0, getWidth(), getHeight());
        storePanel.setBounds(
                getWidth() / 2 - 150,
                getHeight() / 2 - 100,
                300, 200
        );
        //showStore();
    }
    public void showStore(){
        add(shadowPanel, JLayeredPane.PALETTE_LAYER);

        add(storePanel, JLayeredPane.POPUP_LAYER);

        repaint();
        revalidate();
    }
    private void hideStore() {
        // Remove the shadow and store panel
        remove(shadowPanel);
        remove(storePanel);
//        revalidate();
//        repaint();
    }
}
