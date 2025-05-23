package View.MainMenu;

import Controller.MainMenuController;
import View.Oiseau;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;


public class MainMenu extends JPanel {
    private JLabel backgroundLabel; // Use JLabel to hold the animated GIF
    private JButton centerButton;
    //private Clip clip; // Clip pour la musique

    private Oiseau oiseau; // variable membre pour l'instance Oiseau

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
        // Jouer le son dans un thread séparé
        new Thread(() -> jouerSon("Assets/sounds/lancement.wav")).start();
        new Thread(() -> jouerSon("Assets/sounds/NatureMatin.wav")).start();

        // Ici, on ajoute le thread Oiseau.
        // Par exemple, on crée un oiseau commençant à x=800 avec une vitesse de 5
        Oiseau oiseau = new Oiseau(800, 5);
        // On peut également conserver cette référence en tant que variable d'instance si nécessaire

        // Si vous voulez que l'oiseau soit visible, il faut
        // redessiner régulièrement le panel pour mettre à jour son animation.
        // Vous pouvez ajouter un timer pour appeler repaint() sur ce panel.
        Timer timer = new Timer(50, e -> repaint());
        timer.start();
    }

    // Méthode pour jouer un son
    private void jouerSon(String cheminFichier) {
        try {
            URL url = getClass().getClassLoader().getResource(cheminFichier);
            if (url == null) {
                System.err.println("Fichier audio introuvable : " + cheminFichier);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            Clip localClip = AudioSystem.getClip(); // Variable locale
            localClip.open(audioStream);
            localClip.loop(Clip.LOOP_CONTINUOUSLY); // Joue la musique en boucle
            localClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erreur lors de la lecture du son : " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(oiseau != null) {
            oiseau.draw(g);
        }
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
