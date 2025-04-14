package Model.RoundManagement;

import Model.Bank;
import Model.Farm;

import java.util.AbstractMap;

public class Round {

    /* Ecrire les attributs suivants
     * int num_round : numéro de la manche actuelle
     * boolean condition_fin : vaut vrai si la partie est terminée
     * RoundThread thread : thread de manche
     * Farm farm : la ferme du jeu
     */

    private int num_round;
    private boolean condition_fin;
    private RoundThread thread;
    private Farm farm;

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


    public Round(Farm farm) {
        this.farm = farm;
        this.num_round = 0;
        this.condition_fin = false;
        this.thread = new RoundThread(this);

        //paiement
        rbsmt_pret = RBSMT_PRET_INIT;
        prix_nourriture = PRIX_NOURRITURE_INIT;
        salaire_shepherd = SALAIRE_SHEPHERD_INIT;
        loyer = LOYER_INIT;
    }

    /** getter getNbRound de num_round */
    public int getNumRound() {
        return num_round;
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
        start_round();
    }

    /** Fonction start_round qui lance la manche
     * 1. Incrémente le numéro de manche
     * 2. Ré-initialise le thread
     */
    private void start_round() {
        num_round++;
        System.out.println("Round " + num_round + " started.");
        thread.initialize();
        // Augmentation des prix
        prix_nourriture += (int) (prix_nourriture * INFLATION);
        salaire_shepherd += (int) (salaire_shepherd * INFLATION / 2);
        loyer += (int) (loyer * INFLATION);
        rbsmt_pret += (int) (rbsmt_pret * TAUX_PRET);
    }

    /** Fonction end_round qui met fin au round */
    protected void end_round() {
        System.out.println("Round " + num_round + " ended.");
        //TODO fin de manche

        if (condition_fin) {
            end_running();
        } else {
            start_round();
        }
    }

    private void end_running() {
        thread.stopThread();
        //TODO : farm.end_game();
    }

}
