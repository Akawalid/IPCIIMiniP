package View;

import java.awt.*;
import java.util.Random;


public class Oiseau extends Thread {
    private int x;       // Position horizontale
    private int y;       // Position verticale
    private int vitesse; // Vitesse de déplacement vers la gauche
    private static final Random rand = new Random();

    // Variables pour l'animation des ailes
    private boolean wingUp = false;     // État des ailes : lever ou baisser
    private int wingCounter = 0;        // Compteur pour temporiser le battement
    private final int wingToggleInterval = 4; // Intervalle en nombre d'itérations pour changer l'état

    /**
     * Constructeur pour créer un oiseau.
     * @param x La position horizontale initiale.
     * @param vitesse La vitesse de déplacement.
     */
    public Oiseau(int x, int vitesse) {
        this.x = x;
        // Position verticale aléatoire entre 50 et 250 pixels
        this.y = rand.nextInt(200) + 50;
        this.vitesse = vitesse;

        // Démarrage du thread pour lancer l'animation
        this.start();
    }

    /**
     * Méthode de dessin de l'oiseau.
     * Le dessin représente un « V » dont le point central varie pour simuler le battement des ailes.
     * @param g Le contexte graphique utilisé pour le dessin.
     */
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        // Définir un décalage vertical pour le battement de l'aile
        int wingOffset = wingUp ? 7 : 3;

        // Création d'une forme en "V" modifiée pour imiter le mouvement des ailes
        int[] xPoints = { x, x + 5, x + 10 };
        int[] yPoints = { y, y - wingOffset, y };
        g2.drawPolyline(xPoints, yPoints, 3);
    }

    /**
     * Méthode run exécutée par le thread.
     * Elle met à jour la position horizontale et l'état des ailes pour l'animation.
     */
    @Override
    public void run() {
        while (true) {
            // Déplacement de l'oiseau vers la gauche
            x -= vitesse;
            if (x < -20) {
                // Réapparition de l'oiseau à droite de l'écran
                x = 800 + rand.nextInt(100);
                y = rand.nextInt(200) + 50;
            }

            // Mise à jour de l'état des ailes pour simuler le battement
            wingCounter++;
            if (wingCounter >= wingToggleInterval) {
                wingUp = !wingUp;
                wingCounter = 0;
            }

            try {
                // Pause pour une animation fluide
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
