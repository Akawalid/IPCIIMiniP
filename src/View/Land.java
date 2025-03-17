package View;

import Model.Entity;
import Model.Farm;
import Model.FarmAnimals.Sheep;
import Model.Shepherd.FindPath;
import Model.Shepherd.Shepherd;
import Model.Shepherd.ShepherdMovementThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Iterator;

import java.util.Queue;

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

    private final int CELL_SIZE = 32; // Size of each cell in pixels
    private int rows, cols; // Number of rows and columns in the grid
    private Farm farm;

    public Land(Farm farm) {
        super();
        this.farm = farm;

        //In the following, we call set methode once the width and the height of the panel are computed.
        //Th resolution is dynamic, therefor, the width and the height are known at runtime, thats why we've added the
        // listener bellow, (to catch the moment when the resolution is set).
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                set();
            }
        });
    }

    private void set() {
        //Sometimes the layout manager tweaks the resolution multiple times before getting to the final one, sometimes
        //these resolutions can be negative! so we should avoid this case.
        if(getHeight() < 0 || getWidth() < 0) return;

        rows = getHeight() / CELL_SIZE;
        cols = getWidth() / CELL_SIZE;

        setLayout(new GridLayout(rows, cols));

        // Clear the existing cells
        removeAll();

        //we fill the grid by the cells, we paint in dark gray the cells that are out of our model
        //the land doesnt fit exactly on the model, sometimes the model can be bigger therefor, we navigate with arrows
        // but sometimes the model can be smaller which is our case for the moment.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                //if the cell goes out of the models scope, we disactivate it by painting it with dark gray
                if (!farm.validCoordinates(i, cols - j - 1)) {
                    cell.setBackground(Color.gray);
                }
                else if(!farm.getSpot(i, cols - j - 1).isTraversable()){
                    //Maybe another color...
                    cell.setBackground(Color.gray);
                }
                else {
                    //make cell gray on hover
                    cell.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                cell.setBackground(Color.LIGHT_GRAY);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                            cell.setBackground(null);
                        }
                    });
                }
                add(cell);
            }
        }

        revalidate();
        repaint();

        //it is not the right place to call this methode, i've added it for the tests.
        update();
    }

    public void update() {
        // Update the grid by placing entities in the correct cells
        for (Iterator<Entity> it = farm.getEntities(); it.hasNext(); ) {
            Entity e = it.next();

            int r = e.getPosition().getRow();
            int c = e.getPosition().getCol();

            // Convert model coordinates to view index
            int index = rowColOfModelToView(r, c);

            // Check if the index is valid
            if (index >= 0 && index < getComponentCount()) {
                JPanel cell = (JPanel) getComponent(index);

                // Set cell color based on the entity type
                if (e instanceof Shepherd) {
                    cell.setBackground(Color.BLUE);
                } else if (e instanceof Sheep) {
                    cell.setBackground(Color.RED);
                } else {
                    cell.setBackground(Color.GRAY);
                }
            }
        }
    }

    private int rowColOfModelToView(int row, int col) {
        //convert the coordinates of the model to the coordinates of the view
        //the view is a matrix represented by an array (grid[i][j] = grid[i * cols + j])
        return row * cols + cols - col - 1;
    }

    public static void main(String [] args){
        Farm farm = new Farm();
        Shepherd s = new Shepherd("John");
        Sheep shp = new Sheep("sheep1");

        farm.addEntity(s);
        farm.addEntity(shp);

        s.setPosition(farm.getSpot(0, 0));
        shp.setPosition(farm.getSpot(5, 5));

        //create for me obstacles on the map
        farm.getSpot(1, 1).setIsTraversable(false);
        farm.getSpot(2, 2).setIsTraversable(false);
        farm.getSpot(2, 3).setIsTraversable(false);

        Land l = new Land(farm);
        ShepherdMovementThread thrd = new ShepherdMovementThread(s);
        Refresh refresh = new Refresh(l);

        FindPath fp = new FindPath(farm);

        s.setPath(fp.findPath(s.getPosition(), shp.getPosition()));

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(l);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        thrd.start();
        refresh.start();
    }
}