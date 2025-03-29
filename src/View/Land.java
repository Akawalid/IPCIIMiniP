package View;

import Controller.Controller;
import Model.Entity;
import Model.Farm;
import Model.FarmAnimals.Hen;
import Model.FarmAnimals.Sheep;
import Model.FarmAnimals.SimulationUpdateAgeThread;
import Model.Shepherd.FindPath;
import Model.Shepherd.Shepherd;
import Model.Spot;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Iterator;

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
//    private int rows, cols; // Number of rows and columns in the grid
    private Farm farm;

    public Land(Farm farm) {
        super();
        this.farm = farm;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.print("Refresh land \n");
        drawGrid(g);
        drawEnities(g);
    }

    private void drawGrid(Graphics g){
        Color defaultColor = getBackground();
        g.setColor(defaultColor);
        for (int row = 0; row < Farm.HEIGHT; row++) {
            for (int col = 0; col < Farm.WIDTH; col++) {
                if(!farm.getSpot(row, col).isTraversable()) g.setColor(Color.gray);
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
            System.out.println("Entity :" + e.getPosition().getRow() + ", " + e.getPosition().getCol());
            int y = rowOfModelToView(e.getPosition().getRow());
            int x = colOfModelToView(e.getPosition().getCol());
            // Set cell color based on the entity type
            if (e instanceof Shepherd) {
                g.setColor(Color.BLUE);
            } else if (e instanceof Sheep) {
                g.setColor(Color.RED);
                Sheep sheep = (Sheep) e;
                String toolTip = "Espèce : %s %s, Âge : %d, État : %s".formatted(
                        sheep.getSpecies(), sheep.getId(), sheep.getAge(), sheep.getState());
//                cell.setToolTipText(toolTip);
            } else if (e instanceof Hen) {
                g.setColor(Color.YELLOW);
                // Set tooltip text for hen
                Hen hen = (Hen) e;
                g.fillRect(hen.getPosition().getRow() * CELL_SIZE,
                        hen.getPosition().getCol() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                String toolTip = "Espèce : %s %s, Âge : %d, État : %s".formatted(
                        hen.getSpecies(), hen.getId(), hen.getAge(), hen.getState());
//                cell.setToolTipText(toolTip);
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


    public static void main(String[] args){
        //création d'une fenêtre
        JFrame fr = new JFrame();
        Farm farm = new Farm();
        JPanel jp = new JPanel();
        Land land = new Land(farm);
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