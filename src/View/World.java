package View;

import Model.Farm;
import View.ControlPanelComponents.ControlPanel;
import View.ControlPanelComponents.StorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class World extends JLayeredPane {
    //World is a layeredPane, the store and the bank will be displayed as pop up's on the screen that why we use a layeredPane.
    //It is important to note that LayeredPane doesnt not support setPereferredSize, it uses a null Layout, so we have to set
    //the bounds of each element inside manually.
    private static final int STORE_W = 500, STORE_H = 400;
    //World is the page that visualizes the core of the game, it contains the land and the control panel.
    private Farm farm;

    //shadowPanel, is for visualisation purposes, it will be displayed when the store or the bank are displayed.
    private JPanel shadowPanel;
    private StorePanel storePanel;
    //worldPanel, is the main panel that contains the land and the control panel.
    private JPanel worldPanel;

    private Land land;
    private ControlPanel controlPanel;

    public World(Farm farm){
        super();

        this.farm = farm;

        worldPanel = new JPanel();
        worldPanel.setLayout(new BorderLayout());

        // World Panel (Left Column)
        land = new Land(farm);
        worldPanel.add(land, BorderLayout.CENTER);

        // Control Panel (Right Column)
        controlPanel = new ControlPanel(farm);
        worldPanel.add(controlPanel, BorderLayout.EAST);

        add(worldPanel, JLayeredPane.DEFAULT_LAYER); // Default layer


        shadowPanel = new JPanel();
        shadowPanel.setBackground(new Color(0, 0, 0, 150));


        storePanel = new StorePanel();
        //The bounds of wolrdPanel, shadowPanel and storePanel depend on the final resolution of this panel
        //so once this resolution is set, we update the bounds of these panels with the method updateBounds.
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
                getWidth() / 2 - STORE_W/2,
                getHeight() / 2 - STORE_H/2,
                STORE_W, STORE_H
        );
        //showStore();
    }
    public void showStore(){
        //this methode shows the store, by adding the shadow and the store panel to the layeredPane.
        add(shadowPanel, JLayeredPane.PALETTE_LAYER);

        add(storePanel, JLayeredPane.POPUP_LAYER);

        repaint();
        revalidate();
    }
    private void hideStore() {
        // Remove the shadow and store panel
        remove(shadowPanel);
        remove(storePanel);
        revalidate();
        repaint();
    }


    public void update(){
        land.update();
        storePanel.update();
        controlPanel.update();
    }
}
