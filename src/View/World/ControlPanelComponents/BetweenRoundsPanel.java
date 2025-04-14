package View.World.ControlPanelComponents;

import Controller.WorldController;
import Model.Farm;
import View.CustomButton;

import javax.swing.*;

import static Model.RoundManagement.GameStatus.GAME_OVER;

public class BetweenRoundsPanel extends JPanel {

    /* Attributs */
    Farm farm;

    // 1 label par ligne
    private JLabel titleLabel;
    private JLabel balanceLabel;
    private JLabel toPayLabel;
    private JLabel remainingLabel;

    private CustomButton nextRoundButton;
    private CustomButton endGameButton;

    //Bouton de fin de manche (ou de fin de jeu)



    /** contructor */
    public BetweenRoundsPanel(Farm f) {
        farm = f;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Texte
        //définir les attributs sans texte
        titleLabel = new JLabel();
        balanceLabel = new JLabel();
        toPayLabel = new JLabel();
        //variable locale séparateur "-------------"
        JLabel separatorLabel = new JLabel("-------------");
        remainingLabel = new JLabel();

        //ajouter les labels
        add(titleLabel);
        add(balanceLabel);
        add(toPayLabel);
        add(separatorLabel);
        add(remainingLabel);

        //Bouton pour passer à la manche suivante
        nextRoundButton = new CustomButton("Next round");
        add(nextRoundButton);
        endGameButton = new CustomButton("End game");
        //ne pas l'ajouter

        //mettre à jour le texte
        updateText();
    }

    public void updateText(){
        titleLabel.setText("End round " + farm.getRound().getNumRound());
        balanceLabel.setText("Balance: " + farm.getBank().getBalance());
        toPayLabel.setText("To pay: " + farm.getRound().getTotalPayment(farm.getNbAnimalsAndShepherds()));
        remainingLabel.setText("Remaining: " + (farm.getBank().getBalance() - farm.getRound().getTotalPayment(farm.getNbAnimalsAndShepherds())));

        if(farm.getRound().getGameStatus() == GAME_OVER){
            remove(nextRoundButton);
            add(endGameButton);
        }
    }

    //Fonction connect comme dans les autres classes
    public void connect(WorldController c){
        nextRoundButton.addMouseListener(c.getNextRoundHandler());
        endGameButton.addMouseListener(c.getEndGameHandler());
    }
}
