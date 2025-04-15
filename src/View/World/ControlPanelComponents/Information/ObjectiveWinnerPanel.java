package View.World.ControlPanelComponents.Information;


import javax.swing.*;
import java.awt.*;

public class ObjectiveWinnerPanel extends JPanel {

    private final JLabel objectiveLabel;
    private final int winThreshold;

    public ObjectiveWinnerPanel(int winThreshold) {
        this.winThreshold = winThreshold;
        // Configure le JPanel pour qu'il ait un fond jaune fluo
        setBackground(new Color(255, 255, 0));  // Jaune fluo
        setOpaque(true);

        // Configurez une taille préférée si besoin (par exemple 250 x 40)
        setPreferredSize(new Dimension(200, 40));

        // Créer le label avec le texte en majuscules
        objectiveLabel = new JLabel("OBJECTIVE: " + winThreshold);
        objectiveLabel.setFont(new Font("Arial", Font.BOLD, 14));
        objectiveLabel.setForeground(Color.BLACK);

        // Utiliser un layout centré pour le label
        setLayout(new BorderLayout());
        add(objectiveLabel, BorderLayout.CENTER);
    }

}
