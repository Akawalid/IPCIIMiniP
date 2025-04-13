package View.MainMenu;

import Controller.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class MainMenu extends JPanel {
    private JLabel backgroundLabel; // Use JLabel to hold the animated GIF
    private JButton centerButton;

    public MainMenu() {
        JLayeredPane layeredPane = new JLayeredPane();
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        try {
            ImageIcon gifIcon = new ImageIcon(Objects.requireNonNull(
                    getClass().getResource("/Assets/images/bg.gif")
            ));
            backgroundLabel = new JLabel(gifIcon);
            backgroundLabel.setBounds(0, 0, gifIcon.getIconWidth(), gifIcon.getIconHeight());
        } catch (NullPointerException e) {
            e.printStackTrace();
            backgroundLabel = new JLabel("Background missing");
        }

        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setPreferredSize(new Dimension(1300, 700)); // Match your frame size

        centerButton = createImageButton("/Assets/images/start_butt.png");
        centerButton.setBounds(
                (1366 - centerButton.getPreferredSize().width) / 2,
                400, // Adjust Y position as needed
                centerButton.getPreferredSize().width,
                centerButton.getPreferredSize().height
        );

        // Add button to a higher layer
        layeredPane.add(centerButton, JLayeredPane.PALETTE_LAYER);
    }

    private JButton createImageButton(String imagePath) {
        JButton button = new JButton();
        try {
            // Chargement de l'image originale
            Image originalImg = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));

            // Redimensionner l'image à la taille souhaitée
            // Changez ces valeurs selon vos besoins
            int newWidth = 200;  // largeur souhaitée
            int newHeight = 80;  // hauteur souhaitée

            Image scaledImg = originalImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImg));

            // Supprimer tous les styles du bouton
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setMargin(new Insets(0, 0, 0, 0));

            // Définir la taille préférée du bouton pour qu'elle corresponde à l'image
            button.setPreferredSize(new Dimension(newWidth, newHeight));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            button.setText("Play"); // Solution de repli si le chargement de l'image échoue
        }
        return button;
    }

    public void connect(MainMenuController controller) {
        centerButton.addMouseListener(controller.getStartButtonListener());
    }

    public static void main(String[] args) {
        // Create the main JFrame (game window)
        JFrame frame = new JFrame("Game Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 700); // Set window size
        frame.setResizable(false); // Optional: Prevent resizing

        // Add the MainMenu panel (with background image)
        MainMenu mainMenuPanel = new MainMenu();
        frame.add(mainMenuPanel);

        // Center the window on screen
        frame.setLocationRelativeTo(null);

        // Make the window visible
        frame.setVisible(true);
    }


}
