package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import View.Oiseau;

public class TestOiseau {
    public static void main(String[] args) {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Animation Oiseau");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);

        // Ajout du panel d'animation dédié aux oiseaux
        OiseauPanel panel = new OiseauPanel();
        frame.add(panel);
        frame.setVisible(true);

        // Utilisation d'un Timer Swing pour rafraîchir le panel toutes les 30 millisecondes
        Timer timer = new Timer(30, e -> panel.repaint());
        timer.start();
    }
}

/**
 * Panel personnalisé pour afficher et animer les oiseaux.
 */
class OiseauPanel extends JPanel {
    private final ArrayList<Oiseau> oiseaux;

    public OiseauPanel() {
        oiseaux = new ArrayList<>();

        // Création de plusieurs instances d'Oiseau avec des positions et vitesses différentes
        oiseaux.add(new Oiseau(800, 3));
        oiseaux.add(new Oiseau(700, 2));
        oiseaux.add(new Oiseau(600, 1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessin d'un fond "ciel" avec une couleur bleu clair
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dessin de chaque oiseau dans le panel
        for (Oiseau oiseau : oiseaux) {
            oiseau.draw(g);
        }
    }
}
