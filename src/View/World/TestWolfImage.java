package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class TestWolfImage {

    // Méthode utilitaire pour convertir un objet Image en BufferedImage
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Wolf Image");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 200); // Vous pouvez ajuster la taille selon vos besoins

            // Taille souhaitée pour l'image (par exemple 32x32 pixels)
            final int CELL_SIZE = 32;

            // Charger l'image du loup
            BufferedImage wolfImage = null;
            try {
                wolfImage = ImageIO.read(Objects.requireNonNull(
                        TestWolfImage.class.getResource("/Assets/images/Animals/wolf.png")
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (wolfImage == null) {
                System.err.println("L'image du loup n'a pas pu être chargée !");
                return;
            }
            System.out.println("Image chargée, dimensions originales : "
                    + wolfImage.getWidth() + "x" + wolfImage.getHeight());

            // Redimensionner l'image à 32x32 pixels
            Image scaledImage = wolfImage.getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_SMOOTH);
            BufferedImage resizedWolfImage = toBufferedImage(scaledImage);

            // Créer une étiquette (JLabel) avec l'image redimensionnée
            JLabel label = new JLabel(new ImageIcon(resizedWolfImage));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            frame.getContentPane().add(label, BorderLayout.CENTER);

            // Afficher la fenêtre
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
