package Model.RoundManagement;

import Model.Bank;
import Model.Farm;

import javax.swing.*;
import java.util.AbstractMap;

import static Model.RoundManagement.GameStatus.*;

/**
 *  The Round class manages the logic of the game round.
**   It is responsible for initializing prices, handling payments,
*   and advancing the game with each round. */
public class Round {

    /* Ecrire les attributs suivants
     * int num_round : numéro de la manche actuelle
     * RoundThread thread : thread de manche
     * Farm farm : la ferme du jeu
     */

    private int num_round;
    private RoundThread thread;
    private Farm farm;

    private GameStatus gameStatus;

    /* Attributs pour les paiements à réaliser chaque mois */

    // Augmentation des prix
    private static final double INFLATION = 0.06; //hausse des prix chaque manche
    private static final double TAUX_PRET = 1.15; //hausse du taux de remboursement du prêt

    // Initiatlisation des prix
    private static final int DIV_PRET = 10*12; //10 ans
    private static final int RBSMT_PRET_INIT = (int) (Bank.getIinitialBalance() / DIV_PRET);
    private static final int PRIX_NOURRITURE_INIT = 10;
    private static final int SALAIRE_SHEPHERD_INIT = 1000;
    private static final int LOYER_INIT = 500;

    // Variables qui stockeront les prix au fur et à mesure
    private int rbsmt_pret; //valeur à payer pour rembourser le prêt du montant versé en début de jeu

    //L'inflation affecte :
    private int prix_nourriture; //prix de la nourriture pour un animal
    private int salaire_shepherd; //salaire pour une manche d’un shepherd
    private int loyer; //montant du loyer mensuel

    private int total_payment; //total à payer pour la manche
    private static final int WIN_THRESHOLD = 20020;



    public Round(Farm farm) {
        this.farm = farm;
        //this.num_round = 0;
        this.num_round = 1; // Début de la première manche avec le numéro 1
        this.thread = new RoundThread(this);

        //paiement
        rbsmt_pret = RBSMT_PRET_INIT;
        prix_nourriture = PRIX_NOURRITURE_INIT;
        salaire_shepherd = SALAIRE_SHEPHERD_INIT;
        loyer = LOYER_INIT;
        total_payment = 0;
    }

    /** getter getGameStatus */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /** getter getNbRound de num_round */
    public int getNumRound() {
        return num_round;
    }

    public int getProgress(){
        return thread.getCooldown();
    }

    public int getMaxProgress(){
        return thread.getCooldownMax();
    }

    /** getAnimalFeedingPrice(int nbAnimals)
     * = nbAnimals * prix_nourriture
     */
    public int getAnimalFeedingPrice(int nbAnimals) {
        return nbAnimals * prix_nourriture;
    }

    /** getShepherdSalary(nbShepherd)
     * = nbShepherd * salaire_shepherd
     */

    /**
     * Calculates the total salary required for shepherds for the round.
     * @param nbShepherd number of shepherds.
     * @return total salary cost.
     */
    public int getShepherdSalary(int nbShepherd) {
        return nbShepherd * salaire_shepherd;
    }

    /** getRent()
     * = loyer
     */
    public int getRent() {
        return loyer;
    }

    /** getLoanRepayment()
     * = rbsmt_pret
     */
    public int getLoanRepayment() {
        return rbsmt_pret;
    }

    /** getTotalPayment(int feeding, int salary, int rent, int loan) */
    public int getTotalPayment(AbstractMap.SimpleEntry<Integer, Integer> nbAS) {
        return getAnimalFeedingPrice(nbAS.getKey())
                + getShepherdSalary(nbAS.getValue())
                + getRent()
                + getLoanRepayment();
    }

    /** Fonction start_game qui appelle start_round pour la première fois */
    public void start_running() {
        thread.start();
        start_round(); // Première manche démarrée automatiquement
    }

    /** Fonction start_round qui lance la manche
     * 1. Incrémente le numéro de manche
     * 2. Ré-initialise le thread
     */
    public void start_round() {
        //gestion de la manche
        gameStatus = RUNNING;
        //num_round++;
        System.out.println("Round " + num_round + " started.");

        farm.getBank().withdraw(total_payment);
        farm.resume_game(); //relance les threads de la ferme


        // Augmentation des prix
        prix_nourriture += (int) (prix_nourriture * INFLATION);
        salaire_shepherd += (int) (salaire_shepherd * INFLATION / 2);
        loyer += (int) (loyer * INFLATION);
        //rbsmt_pret += (int) (rbsmt_pret * TAUX_PRET);

    }

    /** Fonction end_round qui met fin au round */
    /*protected void end_round() {
        gameStatus = BETWEEN_ROUNDS;
        farm.pause_game(); //met les threads en pause

        System.out.println("Round " + num_round + " ended.");

        //total à payer
        total_payment = getTotalPayment(farm.getNbAnimalsAndShepherds());
        if(farm.getBank().getBalance() < total_payment) gameStatus = GAME_OVER;
        // Arrête le thread pour éviter qu'il ne refasse tourner le cooldown.
        thread.stopThread();


    }*/
    public void end_round() {
        gameStatus = BETWEEN_ROUNDS;
        farm.pause_game(); // Met les threads en pause

        System.out.println("Round " + num_round + " ended.");

        // Calcul du total à payer
        total_payment = getTotalPayment(farm.getNbAnimalsAndShepherds());

        // Condition de défaite : si le solde est insuffisant, le jeu est GAME_OVER
        if (farm.getBank().getBalance() < total_payment) {
            gameStatus = GameStatus.GAME_OVER;
        }
        // Condition de victoire : si le solde atteint ou dépasse le seuil défini, le jeu est gagné
        else if (farm.getBank().getBalance() >= WIN_THRESHOLD) {
            gameStatus = GameStatus.WINNER;
        }

        // Arrête le thread pour éviter qu'il ne refasse tourner le cooldown.
        thread.stopThread();

        // Si le statut est WINNER, on affiche une fenêtre de victoire
        if (gameStatus == GameStatus.WINNER) {
            JOptionPane.showMessageDialog(null, "WINNER", "Victory", JOptionPane.INFORMATION_MESSAGE);
            // Optionnel : quitter l'application
            System.exit(0);
        }
    }



    public void end_game() {
        farm.pause_game();
        gameStatus = CLOSE;
    }

    public void nextRound() {
        // On vérifie que le round précédent est terminé
        if (gameStatus == BETWEEN_ROUNDS) {
            num_round++; // Incrémente le numéro du round

            // Crée un nouveau thread RoundThread
            thread = new RoundThread(this);
            thread.start();

            // Démarre le nouveau round
            start_round();
            // Réinitialiser les dens en supprimant les anciens et en générant de nouveaux
            farm.refreshPredators();

        }
    }

    public void pauseRoundThread(){
        thread.pauseThread();
    }

    public void resumeRoundThread(){
        thread.resumeThread();
    }

    public int getWinThreshold() {
        return WIN_THRESHOLD;
    }
}
