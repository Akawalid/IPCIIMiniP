package View;

import Model.Farm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Land extends JPanel {
    //The size of each cell in the land
    //Note that the land doesn't contain perfectly the Farm of the model, The farm can be bigger that what is displayed
    //on the screen, I will add arrows, to navigate on the land in order to get access to the whole farm
    private final int CELL_SIZE = 32;//16 * 16 pixels

    //number of rows and columns of the grid ayout, it is computed once the dimensions are computed,
    //rows = width/CELL_SIZE, cols = height/CELL_SIZE
    private int rows, cols;
    private Farm farm;
    public Land(Farm farm){
        super();

        this.farm = farm;

        //While creating this object, the final width is not yet computed by the layout manager,
        //But we need it to set the land, so we add a listener that executes after the layout manager has finished
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                rows = getHeight()/CELL_SIZE;
                cols = getWidth()/CELL_SIZE;
                setLayout(new GridLayout(rows, cols));
                setLand();
            }
        });
    }

    private void setLand(){
        //This method fills the screen by the cells of the land which correponds to the spots of the farm, not the whole
        //farm, but it's like a sliding window that shows a part of the farm

        for(int i = 0; i < cols; i++){
            for(int j = 0; j < rows; j++){
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(cell, j, i);
            }
        }
        repaint();
        revalidate();
    }
}
