package View.World;

import Controller.WorldController;
import Model.Entities.Entity;
import Model.Entities.Predators.*;
import Model.Farm;

import View.EntityMetaData;
import Model.Entities.FarmAnimals.Ewe;
import Model.Entities.FarmAnimals.Hen;
import Model.Entities.FarmAnimals.Sheep;
import Model.Entities.Shepherd;
import View.Refresh;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

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
        drawTrees(g);
    }

    private void drawGrid(Graphics g){
        Color defaultColor = getBackground();
        g.setColor(defaultColor);
        for (int row = 0; row < Farm.HEIGHT; row++) {
            for (int col = 0; col < Farm.WIDTH; col++) {
                for(Integer i: Farm.grid1[row][col]){
                    if(i != 15 && i != 3 && i != 4 && i != 16){
                        //We draw the trees at last
                        g.drawImage(gridImages.get(i), colOfModelToView(col),
                                rowOfModelToView(row),
                                null);
                    }
                }
                if(farm.getSpot(row, col).getProtectedArea() > 0){
                    g.setColor(new Color(200, 100, 200, 100));
                    g.fillRect(colOfModelToView(col), rowOfModelToView(row), CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    private void drawTrees(Graphics g){
        for (int row = 0; row < Farm.HEIGHT; row++) {
            for (int col = 0; col < Farm.WIDTH; col++) {
                for(Integer i: Farm.grid1[row][col]){
                    if(i == 15 || i == 3 || i == 4 || i == 16){
                        //We draw the trees at last
                        g.drawImage(gridImages.get(i), colOfModelToView(col),
                                rowOfModelToView(row),
                                null);
                    }
                }
                //if(!farm.getSpot(row, col).isTraversable()){
                    //g.setColor(new Color(255, 0, 0, 100));
                    //g.fillRect(colOfModelToView(col), rowOfModelToView(row), CELL_SIZE, CELL_SIZE);
                //}
            }
        }
    }

    private void drawEnities(Graphics g){
        // Créer une copie thread-safe des entités
        Set<Entity> entitiesSnapshot = new HashSet<>(farm.getEntitiesSet()); // Copie défensive
        List<Den> densSnapshot = new ArrayList<>(farm.getDens()); // Idem pour les terriers
        // Dessiner les entités
        for (Entity e : entitiesSnapshot) { // Utiliser la copie, pas l'original
            int y = rowOfModelToView(e.getPosition().getRow());
            int x = colOfModelToView(e.getPosition().getCol());
            int shadowChoice = 0, imgChoice = 0;

            if (e instanceof Sheep) {
                imgChoice = EntityMetaData.SHEEP_EAT;
                shadowChoice = EntityMetaData.LLAMA_SHADOW;
            } else if (e instanceof Hen) {
                imgChoice = EntityMetaData.HEN_EAT;
                //imgChoice = EntityMetaData.LLAMA_EAT;
                shadowChoice = EntityMetaData.HEN_SHADOW;
            } else if (e instanceof Ewe) {
                imgChoice = EntityMetaData.LLAMA_EAT;
                shadowChoice = EntityMetaData.LLAMA_SHADOW;
            } else if (e instanceof Wolf) {
                //g.setColor(Color.BLACK);
                //imgChoice = EntityMetaData.WOLF;
                //g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                // Récupérer l'image du loup déjà redimensionnée
                BufferedImage wolfImg = EntityMetaData.getWolfAsset();
                if (wolfImg != null) {
                    // redimensionner ici si nécessaire (par défaut 32 x32)
                    Image scaledWolf = wolfImg.getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_SMOOTH);
                    g.drawImage(scaledWolf, x, y, null);
                } else {
                    // En cas d'échec, dessiner un rectangle de secours
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                }
            } else if (e instanceof Fox) {
                BufferedImage foxImg = EntityMetaData.getFoxAsset();
                if (foxImg != null) {
                    // redimensionner ici si nécessaire (par défaut 32 x32)
                    Image scaledFox = foxImg.getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_SMOOTH);
                    g.drawImage(scaledFox, x, y, null);
                } else {
                    // En cas d'échec, dessiner un rectangle de secours
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                }

            //g.setColor(Color.BLACK);
            //imgChoice = EntityMetaData.WOLF;
            //g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

            } else if (e instanceof Shepherd) {
                g.setColor(Color.BLUE);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

            } /*else {
            // Traitement pour les autres entités
            BufferedImage i = EntityMetaData.getAsset(shadowChoice, e.getDirection(), 0);
            int ix = i.getWidth();
            int iy = i.getHeight();
            g.drawImage(i, x - (ix - CELL_SIZE)/2, y - (iy - CELL_SIZE)/2 - EPSILON, null);
            i = EntityMetaData.getAsset(imgChoice, e.getDirection(), 0);
            g.drawImage(i, x - (ix - CELL_SIZE)/2, y - (iy - CELL_SIZE)/2, null);*/

          if (!(e instanceof Wolf || e instanceof Shepherd || e instanceof Fox)) {
                BufferedImage i = EntityMetaData.getAsset(shadowChoice, e.getDirection(), 0);
                int ix = i.getWidth();
                int iy = i.getHeight();
                g.drawImage(i, x - (ix - CELL_SIZE)/2, y - (iy - CELL_SIZE)/2 - EPSILON, null);
                i = EntityMetaData.getAsset(imgChoice, e.getDirection(), 0);
                g.drawImage(i, x - (ix - CELL_SIZE)/2, y - (iy - CELL_SIZE)/2, null);
            }
        }

        // Dessiner les terriers
        for (Den d : densSnapshot) { // Utiliser la copie
            int y = rowOfModelToView(d.getPosition().getRow());
            int x = colOfModelToView(d.getPosition().getCol());

            // Choix de la couleur selon le type de terrier et son état actif
            if (d instanceof WolfDen) {
                // Pour un terrier de loup : gris foncé (active) ou gris foncé semi-transparent (inactive)
                if(d.isActive()){
                    g.setColor(new Color(64, 64, 64)); // Gris foncé
                } else {
                    g.setColor(new Color(64, 64, 64, 100)); // Même couleur mais avec transparence
                }
            }
            else if (d instanceof FoxDen) {
                // Pour un terrier de renard : rouge brique foncé (active) ou orange foncé semi-transparent (inactive)
                if(d.isActive()){
                    g.setColor(new Color(150, 50, 50)); // rouge brique  foncé (RGB 255, 140, 0)
                } else {
                    g.setColor(new Color(150, 50, 50, 100)); // Même couleur avec transparence
                }
            }
            else {
                // Pour toute autre classe de Den, utiliser la couleur par défaut existante
                g.setColor(d.isActive() ? new Color(0x283618) : new Color(40, 54, 24, 70));
            }

            // Dessiner le terrier sous forme d'ovale dans la case
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

        // Vérifier que row et col sont dans les limites valides
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