package View.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Mosaic extends JPanel implements ActionListener {
    private static final int TILE_SIZE = 20; // Size of each tile
    private static final int GRID_ROWS = 700/30; // Number of rows in the grid
    private static final int GRID_COLS = 1300/30; // Number of columns in the grid
    private static final int MEAN1X = GRID_ROWS / 5, STD1X = 5, MEAN1Y = GRID_COLS/5, STD1Y = 3;
    private static final int MEAN2X = 4 * GRID_ROWS / 5, STD2X = 3, MEAN2Y = 4 * GRID_COLS / 5, STD2Y = 4;
    private static final int MEAN3X = 2 * GRID_ROWS / 5, STD3X = 4, MEAN3Y = 2 * GRID_COLS / 5, STD3Y = 3;
    private Random random;
    private Color[][] grid;
    private CopyOnWriteArrayList<ColorTile> activeTiles = new CopyOnWriteArrayList<>();
    private Timer animationTimer;

    private static class ColorTile {
        int row, col;
        Color color;
        int alpha = 0;
        boolean descending = false;

        ColorTile(int row, int col, Color color) {
            this.row = row;
            this.col = col;
            this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
        }

        boolean update() {
            if (descending) {
                if (alpha > 5) {
                    alpha -= 15;
                    color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                    return true;
                }
                return false; // Remove this tile
            } else {
                if (alpha < 250) {
                    alpha += 15;
                    color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                    return true;
                }
                descending = true;
                return true;
            }
        }
    }

    public Mosaic() {
        random = new Random();
        grid = new Color[GRID_ROWS][GRID_COLS];
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                grid[row][col] = new Color(0, 0, 0, 0); // Initialize with transparent
            }
        }
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1300, 700));

        // Timer for animation updates - 30ms for ~33 FPS
        animationTimer = new Timer(30, this);
        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the current state of the grid
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                if (grid[row][col].getAlpha() > 0) {
                    g.setColor(grid[row][col]);
                    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void addNewTile() {
        // Randomly add a new tile based on Gaussian distribution
        int row = (int) random.nextGaussian(MEAN1X, STD1X);
        int col = (int) random.nextGaussian(MEAN1Y, STD1Y);
        if (row >= 0 && row < GRID_ROWS && col >= 0 && col < GRID_COLS && grid[row][col].getAlpha() == 0) {
            ColorTile tile = new ColorTile(row, col, new Color(0x3a0ca3));
            activeTiles.add(tile);
            grid[row][col] = tile.color;
        }

        // Randomly add a new tile based on Gaussian distribution
        row = (int) random.nextGaussian(MEAN2X, STD2X);
        col = (int) random.nextGaussian(MEAN2Y, STD2Y);
        if (row >= 0 && row < GRID_ROWS && col >= 0 && col < GRID_COLS && grid[row][col].getAlpha() == 0) {
            ColorTile tile = new ColorTile(row, col, new Color(0xf72585));
            activeTiles.add(tile);
            grid[row][col] = tile.color;
        }

        // Randomly add a new tile based on Gaussian distribution
        row = (int) random.nextGaussian(MEAN3X, STD3X);
        col = (int) random.nextGaussian(MEAN3Y, STD3Y);
        if (row >= 0 && row < GRID_ROWS && col >= 0 && col < GRID_COLS && grid[row][col].getAlpha() == 0) {
            ColorTile tile = new ColorTile(row, col, new Color(0x184e77));
            activeTiles.add(tile);
            grid[row][col] = tile.color;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        // Update existing tiles
        for (ColorTile tile : activeTiles) {
            boolean isActive = tile.update();
            if (isActive) {
                grid[tile.row][tile.col] = tile.color;

            } else {
                grid[tile.row][tile.col] = new Color(0, 0, 0, 0);
                activeTiles.remove(tile);
            }
        }

        // Randomly add new tiles (about 10% chance per frame)
        if (random.nextDouble() < 0.8) {
            addNewTile();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mosaic Animation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 700);
            frame.setResizable(false);

            Mosaic mosaic = new Mosaic();
            frame.add(mosaic);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}