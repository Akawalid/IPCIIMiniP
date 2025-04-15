package View;

import java.awt.*;
import java.util.Random;


public class Oiseau extends Thread {
    private int x;       // Position horizontale
    private int y;       // Position verticale
    private int vitesse; // Vitesse de d�placement vers la gauche
    private static final Random rand = new Random();

    // Variables pour l'animation des ailes
    private boolean wingUp = false;     // �tat des ailes : lever ou baisser
    private int wingCounter = 0;        // Compteur pour temporiser le battement
    private final int wingToggleInterval = 4; // Intervalle en nombre d'it�rations pour changer l'�tat

    /**
     * Constructeur pour cr�er un oiseau.
     * @param x La position horizontale initiale.
     * @param vitesse La vitesse de d�placement.
     */
    public Oiseau(int x, int vitesse) {
        this.x = x;
        // Position verticale al�atoire entre 50 et 250 pixels
        this.y = rand.nextInt(200) + 50;
        this.vitesse = vitesse;

        // D�marrage du thread pour lancer l'animation
        this.start();
    }

    /**
     * M�thode de dessin de l'oiseau.
     * Le dessin repr�sente un � V � dont le point central varie pour simuler le battement des ailes.
     * @param g Le contexte graphique utilis� pour le dessin.
     */
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        // D�finir un d�calage vertical pour le battement de l'aile
        int wingOffset = wingUp ? 7 : 3;

        // Cr�ation d'une forme en "V" modifi�e pour imiter le mouvement des ailes
        int[] xPoints = { x, x + 5, x + 10 };
        int[] yPoints = { y, y - wingOffset, y };
        g2.drawPolyline(xPoints, yPoints, 3);
    }

    /**
     * M�thode run ex�cut�e par le thread.
     * Elle met � jour la position horizontale et l'�tat des ailes pour l'animation.
     */
    @Override
    public void run() {
        while (true) {
            // D�placement de l'oiseau vers la gauche
            x -= vitesse;
            if (x < -20) {
                // R�apparition de l'oiseau � droite de l'�cran
                x = 800 + rand.nextInt(100);
                y = rand.nextInt(200) + 50;
            }

            // Mise � jour de l'�tat des ailes pour simuler le battement
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
