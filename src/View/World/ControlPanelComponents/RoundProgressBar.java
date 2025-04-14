package View.World.ControlPanelComponents;

import Model.RoundManagement.Round;

import javax.swing.*;
import java.awt.*;

public class RoundProgressBar extends JPanel {

    /* Attributes */
    private final Round round;
    private final JProgressBar progressBar;

    /* Constructor */
    public RoundProgressBar(Round round) {
        this.round = round;

        //Créer une progress bar qui contient le texte "round i" avec i le numéro du round
        progressBar = new JProgressBar();
        progressBar.setMaximum(round.getMaxProgress());
        progressBar.setStringPainted(true);
        actualizeProgressBar();
        add(progressBar);
    }

    public void actualizeProgressBar(){
        int progress = round.getProgress();
        int maxProgress = round.getMaxProgress();

        //texte de la barre de progression
        progressBar.setString("Round " + round.getNumRound());
        //actualiser le contenu de la barre de progression
        progressBar.setValue(progress);

        //Mettre la barre de progrès de couleur
        // de sorte que la barre passe de verte à rouge
        // au fur et à mesure de l'avancement
        float ratio = (float) progress / maxProgress;
        int red, green;
        int blue = 0;
        if(ratio < 0.5){
            red = (int) (255 * 2 * ratio);
            green = 255;
        }
        else{
            red = 255;
            green = (int) (255 * (1 - 2 * (ratio - 0.5)));
        }
        progressBar.setForeground(new Color(red, green, blue));
    }

    /** fonction qui met à jour la barre de progression avec repaint */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        actualizeProgressBar();
    }

}
