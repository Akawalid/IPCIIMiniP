package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniPaintApp extends JFrame {
    private Color selectedColor = Color.BLACK; // Default color
    private JPanel gridPanel; // Panel for the 16x16 grid
    private JPanel colorPanel; // Panel for color options

    public MiniPaintApp() {
        setTitle("Mini Paint App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the 16x16 grid
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(16, 16));
        initializeGrid();

        // Create the color options panel
        colorPanel = new JPanel();
        colorPanel.setLayout(new FlowLayout());
        addColorOptions();

        // Add components to the frame
        add(gridPanel, BorderLayout.CENTER);
        add(colorPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializeGrid() {
        for (int i = 0; i < 16 * 16; i++) {
            JPanel pixel = new JPanel();
            pixel.setBackground(Color.WHITE);
            pixel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // Add click listener to change color
            pixel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    pixel.setBackground(selectedColor);
                }
            });

            gridPanel.add(pixel);
        }
    }

    private void addColorOptions() {
        // Predefined colors
        Color[] colors = {
                Color.BLACK, Color.RED, Color.GREEN, Color.BLUE,
                Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN
        };

        for (Color color : colors) {
            JButton colorButton = new JButton();
            colorButton.setBackground(color);
            colorButton.setPreferredSize(new Dimension(40, 40));
            colorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedColor = color; // Set the selected color
                }
            });
            colorPanel.add(colorButton);
        }

        // Add a "Custom Color" button to open JColorChooser
        JButton customColorButton = new JButton("Custom Color");
        customColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color customColor = JColorChooser.showDialog(
                        MiniPaintApp.this, "Choose a Color", selectedColor);
                if (customColor != null) {
                    selectedColor = customColor; // Set the selected color
                }
            }
        });
        colorPanel.add(customColorButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MiniPaintApp());
    }
}