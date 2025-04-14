package View.World;

import Model.Farm;
import Controller.WorldController;
import View.World.ControlPanelComponents.ControlPanel;
//import View.World.ControlPanelComponents.Information.PurchaseType;
import View.World.ControlPanelComponents.Information.PurchaseType;
import View.World.ControlPanelComponents.StorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class World extends JLayeredPane {
    //World is the page that visualizes the core of the game, it contains the land and the control panel.
    public static final int UPDATE_ACTIVE_ENTITY = 0;
    private Farm farm;

    //shadowPanel, is for visualisation purposes, it will be displayed when the store or the bank are displayed.
    private JPanel shadowPanel;
    private StorePanel storePanel;
    //worldPanel, is the main panel that contains the land and the control panel.
    private JSplitPane worldPanel;

    private Land land;
    private ControlPanel controlPanel;
    private boolean inMovementChoiceState;
    private PurchaseType purchaseMode = null; /// The current purchase mode.
   // private HashMap<Entity, EntityMetaData> entitiesMetaData;

    public World(Farm farm){
        super();

        this.farm = farm;
        //entitiesMetaData = new HashMap<>();

        worldPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // World Panel (Left Column)
        land = new Land(farm);
        worldPanel.add(land, JSplitPane.LEFT);

        // Control Panel (Right Column)
        controlPanel = new ControlPanel(farm, this);
        controlPanel.setPreferredSize(new Dimension(180, 200));
        worldPanel.add(controlPanel, JSplitPane.RIGHT);

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
//        storePanel.setBounds(
//                getWidth() / 2 - StorePanel.STORE_W/2,
//                getHeight() / 2 - StorePanel.STORE_H/2,
//                StorePanel.STORE_W, StorePanel.STORE_H
//        );
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

    public void connect(WorldController c){
        land.connect(c);
        controlPanel.connect(c);
    }

    public void inform(final int MESSAGE){
        //Observer, Observable pattern
        switch(MESSAGE) {
            case UPDATE_ACTIVE_ENTITY:
                controlPanel.updateActiveEntity();
                break;
        default:
            break;
        }
    }

    public boolean getInMovementChoiceState(){return inMovementChoiceState;}
    public void setInMovementChoiceState(boolean state){inMovementChoiceState = state;}

    public Land getLand(){return land;}

    //Sets the current purchase mode.
    public void setPurchaseMode(PurchaseType pt){
        this.purchaseMode = pt;
    }
    public PurchaseType getPurchaseMode() {
        return this.purchaseMode;
    }
}
