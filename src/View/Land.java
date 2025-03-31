package View;

import Controller.Controller;
import Model.Entity;
import Model.Farm;
import Model.FarmAnimals.Ewe;
import Model.FarmAnimals.Hen;
import Model.FarmAnimals.Sheep;
import Model.Shepherd.Shepherd;
import Model.Spot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

public class Land extends JPanel {
    //This class represents the grid on the screen, where the game will be played.
    /*
    on Land:
    +---------------------------------------------------+
    |    ...                                  |0, 1|0, 0|
    |    ...                                  |1, 1|1, 0|
    ...
    |    ...                                  |n, 1|n, 0|
     +---------------------------------------------------+

     in the model: (is the simple 2D matrix, to make the computations easy, you should not worry about this, because
     //I've created methods RowColOfModelToView, plug coordinates of the view, the mehtod returns the index in the grid
        +---------------------------------------------------+
        |0, 0|0, 1|    ...
        |1, 0|1, 1|    ...
        ...
        |n, 0|n, 1|    ...
        +---------------------------------------------------+
     */

    public static final int CELL_SIZE = 64,// Size of each cell in pixels
            WIDTH= CELL_SIZE * Farm.WIDTH,
            HEIGHT= CELL_SIZE * Farm.HEIGHT;
    private Farm farm;
    // The data structure below represents the spots we are highlighting on the game map.
    // Each spot will be highlighted using the color of the entity that will pass through it.

    // Problem 1: Some spots may be traversed by multiple entities. How do we color them?
    // Solution: We use the color of the first entity that will cross the spot.

    // Problem 2: What if multiple entities will cross the same spot simultaneously?
    // Solution: In this case, we use the color of the shepherd closest to its target
    // (shepherds have priority).

        // Note: We only consider shepherds here because:
    // 1. Predators can never occupy the same spot as shepherds
    // 2. Other entities don't move (currently)
    // If we change movement rules later, we'll adapt the scheduling method accordingly.
    private HashMap<Spot, HashMap<Integer, Entity>> pathHighlights;
    //The hashmap below corresponds to the data that are held by the view but not the model
    //For instance, the images of each entity, their color...
    private HashMap<Entity, EntityMetaData> entitiesMetaData;

    public Land(Farm farm, HashMap<Entity, EntityMetaData> entitiesMetaData) {
        super();
        this.farm = farm;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        pathHighlights = new HashMap<>();
        this.entitiesMetaData = entitiesMetaData;
        // Active les tooltips pour ce composant
        setToolTipText("");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawEnities(g);
    }

    private void drawGrid(Graphics g){
        Color defaultColor = getBackground();
        g.setColor(defaultColor);
        for (int row = 0; row < Farm.HEIGHT; row++) {
            for (int col = 0; col < Farm.WIDTH; col++) {

                if(pathHighlights.containsKey(farm.getSpot(row, col))){
                    Entity e = scheduler(pathHighlights.get(farm.getSpot(row, col)));
                    if(pathHighlights.get(farm.getSpot(row, col)).isEmpty()) pathHighlights.remove(farm.getSpot(row, col));
                    assert(e != null);
                    assert(entitiesMetaData.get(e) != null);
                    g.setColor(entitiesMetaData.get(e).applyOpacityForColor());
                }
                else if(!farm.getSpot(row, col).isTraversable()) g.setColor(Color.gray);
                else g.setColor(defaultColor);

                g.fillRect(colOfModelToView(col), rowOfModelToView(row), CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(colOfModelToView(col), rowOfModelToView(row), CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawEnities(Graphics g){
        for (Iterator<Entity> it = farm.getEntities(); it.hasNext(); ) {
            Entity e = it.next();
            int y = rowOfModelToView(e.getPosition().getRow());
            int x = colOfModelToView(e.getPosition().getCol());
            // Set cell color based on the entity type
            if (e instanceof Shepherd) {
                //This doesn't make any sens for the moment, but it will be more meaningful, when we work with images
                //g.setColor(entitiesMetaData.get(e).getColor());
                g.setColor(Color.BLUE);
            } else if (e instanceof Sheep) {
                //g.setColor(entitiesMetaData.get(e).getColor());
                g.setColor(Color.RED);
                Sheep sheep = (Sheep) e;
                //String toolTip = "Espèce : %s %s, Âge : %d, État : %s".formatted(
                       // sheep.getSpecies(), sheep.getId(), sheep.getAge(), sheep.getState());
//                cell.setToolTipText(toolTip);
            } else if (e instanceof Hen) {
                g.setColor(Color.YELLOW);
                // Set tooltip text for hen
                //Hen hen = (Hen) e;
                //g.fillRect(hen.getPosition().getRow() * CELL_SIZE,
                        //hen.getPosition().getCol() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                //String toolTip = "Espèce : %s %s, Âge : %d, État : %s".formatted(
                        //hen.getSpecies(), hen.getId(), hen.getAge(), hen.getState());
//                cell.setToolTipText(toolTip);
            } else if (e instanceof Ewe){
                g.setColor(Color.PINK);
            }
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
        g.setColor(null);
    }

    private int rowOfModelToView(int row){return row * CELL_SIZE;}
    private int colOfModelToView(int col){return (Farm.WIDTH - col - 1) * CELL_SIZE;}
    public void connect(Controller c){
        addMouseListener(c.coordinatesHandler());
    }

    public void addSpotEntity(Spot s, Entity e, int order){
        assert(s != null && e != null);

        HashMap<Integer, Entity> tmp = pathHighlights.get(s);
        if(tmp != null) tmp.put(order, e);
        else pathHighlights.put(s, new HashMap<>(Collections.singletonMap(order, e)));
    }

    private Entity scheduler(HashMap<Integer, Entity> mp) {
        assert(mp != null);

        //We assume that the priority of null is +inf
        Entity weFollowItsColor=null;
        //No priority is negative! by convention
        int min = Integer.MAX_VALUE;
        Iterator<Map.Entry<Integer, Entity>> iterator = mp.entrySet().iterator();
        while (iterator.hasNext()) {
            //Invariant
            /*
                > foreach Entity in toSortAccordingToPriority:
                    it will pass sooner or at the same time with the previous entities
                > for all entity previously occurred, the distance between the prior and te spot is equal or bigger than min
            */
            Map.Entry<Integer, Entity> entry = iterator.next();
            Integer reverseOrderOfSpotInEntitiesPath = entry.getKey();
            Entity entity = entry.getValue();
            if(entity.getPathSize() - reverseOrderOfSpotInEntitiesPath <= 0) {
                //entity.getPathSize() - reverseOrderOfSpotInEntitiesPath corresponds to the distance between
                //entity and the spot to be colored
                iterator.remove();
            }

            if(entity.getPathSize() - reverseOrderOfSpotInEntitiesPath < min) {
                min = entity.getPathSize() - reverseOrderOfSpotInEntitiesPath;
                weFollowItsColor = entity;
            }
            else if (entity.getPathSize() - reverseOrderOfSpotInEntitiesPath == min) {
                //Lexicographic order
                if(weFollowItsColor == null || weFollowItsColor.compareTo(entity) > 0)
                    weFollowItsColor = entity;

                else if(weFollowItsColor.compareTo(entity) == 0)
                    //Invariant on the uniqueness of priorities is violated
                    assert(false);

            }
        }

        return weFollowItsColor;
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        // Convert view coordinates to model coordinates
        int x = event.getX();
        int y = event.getY();

        // Convert pixel coordinates to grid indices
        // La colonne est calculée en partant de la droite de la grille
        int col = Farm.WIDTH - Math.floorDiv(x, CELL_SIZE) - 1;
        int row = Math.floorDiv(y, CELL_SIZE);

        // Vérifier que row et col sont dans les limites valides
        if (row < 0 || row >= Farm.HEIGHT || col < 0 || col >= Farm.WIDTH) {
            return null;
        }

        // Use the getEntityInSpot method to find the entity on this cell
        Entity entity = farm.getEntityInSpot(row, col);
        if (entity != null) {
            if (entity instanceof Shepherd) {
                // TODO : afficher les informations du Shepherd
                return "Shepherd " ;
            } else if (entity instanceof Sheep) {
                Sheep sheep = (Sheep) entity;
                return "Sheep: " + sheep.getSpecies() + " " + sheep.getId() +
                        ", Age: " + sheep.getAge() + ", State: " + sheep.getState();
            } else if (entity instanceof Hen) {
                Hen hen = (Hen) entity;
                return "Hen: " + hen.getSpecies() + " " + hen.getId() +
                        ", Age: " + hen.getAge() + ", State: " + hen.getState();
            } else if (entity instanceof Ewe) {
                Ewe ewe = (Ewe) entity;
                return "Hen: " + ewe.getSpecies() + " " + ewe.getId() +
                        ", Age: " + ewe.getAge() + ", State: " + ewe.getState();
            }
        }
        // If no entity is found on the cell, no tooltip is displayed
        return null;
    }


    public static void main(String[] args){
        //création d'une fenêtre
        JFrame fr = new JFrame();
        Farm farm = new Farm();
        JPanel jp = new JPanel();
        Land land = new Land(farm, new HashMap<>());
        jp.add(land);
        fr.add(jp);
        //création fenêtre fin
        fr.setTitle("Farm");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
}