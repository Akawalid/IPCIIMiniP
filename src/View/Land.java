package View;

import Controller.Controller;
import Model.Entity;
import Model.Farm;
import Model.FarmAnimals.Ewe;
import Model.FarmAnimals.Hen;
import Model.FarmAnimals.Sheep;
import Model.Predators.Den;
import Model.Predators.Wolf;
import Model.Predators.WolfDen;
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

    public static final int CELL_SIZE = 32,// Size of each cell in pixels
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

    public Land(Farm farm) {
        super();
        this.farm = farm;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
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

                if(!farm.getSpot(row, col).isTraversable()) {
                    g.setColor(Color.MAGENTA);
                }
                else if(farm.getSpot(row, col).isProtectedArea()) g.setColor(new Color(200, 100, 200, 100));
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
                //String toolTip = "Esp�ce : %s %s, �ge : %d, �tat : %s".formatted(
                       // sheep.getSpecies(), sheep.getId(), sheep.getAge(), sheep.getState());
//                cell.setToolTipText(toolTip);
            } else if (e instanceof Hen) {
                g.setColor(Color.YELLOW);
                // Set tooltip text for hen
                //Hen hen = (Hen) e;
                //g.fillRect(hen.getPosition().getRow() * CELL_SIZE,
                        //hen.getPosition().getCol() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                //String toolTip = "Esp�ce : %s %s, �ge : %d, �tat : %s".formatted(
                        //hen.getSpecies(), hen.getId(), hen.getAge(), hen.getState());
//                cell.setToolTipText(toolTip);
            } else if (e instanceof Ewe){
                g.setColor(Color.PINK);
            } else if (e instanceof Wolf) {
                g.setColor(Color.BLACK);
            }
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
        for (Iterator<Den> it = farm.getDens(); it.hasNext(); ) {
            Den d = it.next();
            int y = rowOfModelToView(d.getPosition().getRow());
            int x = colOfModelToView(d.getPosition().getCol());
            if(d.isActive())
                g.setColor(Color.green);
            else
                g.setColor(new Color(0, 255, 9, 100));
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
        g.setColor(null);
    }

    private int rowOfModelToView(int row){return row * CELL_SIZE;}
    private int colOfModelToView(int col){return (Farm.WIDTH - col - 1) * CELL_SIZE;}
    public void connect(Controller c){
        addMouseListener(c.coordinatesHandler());
    }


    @Override
    public String getToolTipText(MouseEvent event) {
        // Convert view coordinates to model coordinates
        int x = event.getX();
        int y = event.getY();

        // Convert pixel coordinates to grid indices
        // La colonne est calcul�e en partant de la droite de la grille
        int col = Farm.WIDTH - Math.floorDiv(x, CELL_SIZE) - 1;
        int row = Math.floorDiv(y, CELL_SIZE);

        // V�rifier que row et col sont dans les limites valides
        if (row < 0 || row >= Farm.HEIGHT || col < 0 || col >= Farm.WIDTH) {
            return null;
        }

        // Use the getEntityInSpot method to find the entity on this cell
        Entity entity = farm.getEntityInSpot(row, col);
        if (entity != null) {
            if (entity instanceof Shepherd) {
                // TODO : afficher les informations du Shepherd
                return "Shepherd ";
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
            } else if (entity instanceof Wolf) {
                return "Wolf";
            }
        }
        // If no entity is found on the cell, no tooltip is displayed
        return null;
    }



    public static void main(String[] args){
        //cr�ation d'une fen�tre
        JFrame fr = new JFrame();
        Farm farm = new Farm();
        JPanel jp = new JPanel();
        Land land = new Land(farm);
        jp.add(land);
        fr.add(jp);
        //cr�ation fen�tre fin
        fr.setTitle("Farm");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
}