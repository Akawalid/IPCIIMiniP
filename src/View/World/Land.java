package View.World;

import Controller.WorldController;
import Model.Entities.Entity;
import Model.Farm;

import View.EntityMetaData;
import Model.Entities.FarmAnimals.Ewe;
import Model.Entities.FarmAnimals.Hen;
import Model.Entities.FarmAnimals.Sheep;
import Model.Entities.Predators.Den;
import Model.Entities.Predators.Wolf;
import Model.Entities.Shepherd;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
            HEIGHT= CELL_SIZE * Farm.HEIGHT,
            EPSILON = 3;
    private Farm farm;
    private HashMap<Integer, Image> gridImages;

    public Land(Farm farm) {
        super();
        this.farm = farm;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        setToolTipText("");

        gridImages = new HashMap<>();
        for (int i = 0; i < Farm.HEIGHT; i++) {
            for (int j = 0; j < Farm.WIDTH; j++) {
                for (Integer k : Farm.grid1[i][j]) {
                    try {;
                        if(gridImages.containsKey(k)) continue;
                        String imagePath = "/Assets/images/Tiles/tile_00" + String.format("%0" + 2 + "d", k) + ".png";
                        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
                        gridImages.put(k, image);
                    } catch (IOException | IllegalArgumentException e) {
                        System.err.println("ERROR: Failed to load image for tile ID " + k);
                        e.printStackTrace();
                    }
                }
            }
        }

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

//                else if(farm.getSpot(row, col).isProtectedArea()) g.setColor(new Color(200, 100, 200, 100));
//                else g.setColor(defaultColor);
//
//                g.fillRect(colOfModelToView(col), rowOfModelToView(row), CELL_SIZE, CELL_SIZE);
//                g.setColor(Color.BLACK);
//                g.drawRect(colOfModelToView(col), rowOfModelToView(row), CELL_SIZE, CELL_SIZE);

                for(Integer i: Farm.grid1[row][col]){
                    g.drawImage(gridImages.get(i), colOfModelToView(col),
                            rowOfModelToView(row),
                            null);
                }
                if(farm.getSpot(row, col).getProtectedArea() > 0){
                    g.setColor(new Color(200, 100, 200, 100));
                    g.fillRect(colOfModelToView(col), rowOfModelToView(row), CELL_SIZE, CELL_SIZE);
                }
                /* permet d'afficher en rose les cases non traversables
                if(!farm.getSpot(row, col).isTraversable()) {
                    g.setColor(Color.MAGENTA);
                    g.fillRect(colOfModelToView(col), rowOfModelToView(row), CELL_SIZE, CELL_SIZE);
                }
                */
            }
        }
    }

    private void drawEnities(Graphics g){
        for (Iterator<Entity> it = farm.getEntities(); it.hasNext(); ) {
            Entity e = it.next();
            int y = rowOfModelToView(e.getPosition().getRow());
            int x = colOfModelToView(e.getPosition().getCol());
            int shadowChoixe = 0, imgChoice = 0;
            if (e instanceof Sheep) {
                imgChoice = EntityMetaData.SHEEP_EAT;
                shadowChoixe = EntityMetaData.LLAMA_SHADOW;
            } else if(e instanceof Hen){
                imgChoice = EntityMetaData.LLAMA_EAT;
                shadowChoixe = EntityMetaData.LLAMA_SHADOW;

            } else if(e instanceof Ewe){
                imgChoice = EntityMetaData.COW_EAT;
                shadowChoixe = EntityMetaData.COW_SHADOW;
            } else if(e instanceof Wolf){
                BufferedImage i = EntityMetaData.getAsset(EntityMetaData.WOLF, e.getDirection(), 0);
                g.drawImage(i, x, y, null);
            } else if(e instanceof Shepherd)
            {
                g.setColor(Color.BLUE);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
            if(!(e instanceof Wolf || e instanceof Shepherd)){
                BufferedImage i = EntityMetaData.getAsset(shadowChoixe, e.getDirection(), 0);
                int ix = i.getWidth();
                int iy = i.getHeight();
                g.drawImage(i, x  - (ix - CELL_SIZE)/2, y - (iy - CELL_SIZE)/2 - EPSILON, null);
                ix = i.getWidth();
                iy = i.getHeight();
                i = EntityMetaData.getAsset(imgChoice, e.getDirection(), 0);
                g.drawImage(i, x  - (ix - CELL_SIZE)/2, y - (iy - CELL_SIZE)/2, null);
            }


        }
        for (Iterator<Den> it = farm.getDens(); it.hasNext(); ) {
            Den d = it.next();
            int y = rowOfModelToView(d.getPosition().getRow());
            int x = colOfModelToView(d.getPosition().getCol());
            if(d.isActive())
                g.setColor(new Color(0x283618));
            else
                g.setColor(new Color(16 * 2 + 8, 3 * 16 + 6, 16 + 8, 100));
            g.fillOval(x, y, CELL_SIZE, CELL_SIZE);
        }
        g.setColor(null);
    }

    private int rowOfModelToView(int row){return row * CELL_SIZE;}
    private int colOfModelToView(int col){return (Farm.WIDTH - col - 1) * CELL_SIZE;}
    public void connect(WorldController c){
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