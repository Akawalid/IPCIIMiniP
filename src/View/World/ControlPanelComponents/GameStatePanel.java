package View.World.ControlPanelComponents;
import Model.Farm;
import Controller.WorldController;
import Model.RoundManagement.Round;
import View.World.ControlPanelComponents.Information.ObjectiveWinnerPanel;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;

public class GameStatePanel extends JPanel {
    private final Farm farm;
    private final Round round;
    private final JLabel objectiveFeeding;
    private final JLabel objectiveShepherd;
    private final JLabel objectiveRent;
    private final JLabel objectiveLoan;
    private final JLabel objectiveTotal;
    private final JLabel balanceLabel;
    // Label pour afficher l'objectif (WIN_THRESHOLD)
// Maintenant, au lieu d'un label pour l'objectif, on aura un ObjectivePanel
    private final ObjectiveWinnerPanel objectiveWinnerPanel;

    //constructeur
    public GameStatePanel(Farm f){
        //this method displays the game state,in which we count, the store, the bank, the day and the objective
        farm = f;
        round = farm.getRound();

        RoundProgressBar roundProgressBar = new RoundProgressBar(round);
        balanceLabel = new JLabel("Balance:" +  farm.getBank().getBalance());

        //Objective panel : one lign for each objective
        JPanel objectivePanel = new JPanel();
        JLabel objectiveTitle = new JLabel("End round payment");
        objectiveFeeding = new JLabel();
        objectiveShepherd = new JLabel();
        objectiveRent = new JLabel();
        objectiveLoan = new JLabel();
        JLabel objectiveSeparator = new JLabel("-------------------------");
        objectiveTotal = new JLabel();
        update_objective_text();

        //layout and adding objects for objectivePanel
        objectivePanel.setLayout(new BoxLayout(objectivePanel, BoxLayout.Y_AXIS));
        objectivePanel.add(objectiveTitle);
        objectivePanel.add(objectiveFeeding);
        objectivePanel.add(objectiveShepherd);
        objectivePanel.add(objectiveRent);
        objectivePanel.add(objectiveLoan);
        objectivePanel.add(objectiveSeparator);
        objectivePanel.add(objectiveTotal);


        // Création du label pour l'objectif général (WIN_THRESHOLD)
        // Ici, on utilise directement la valeur 20050, mais vous pourriez la récupérer depuis Round si elle y est accessible.
        // Nouveau panneau dédié à l'affichage de l'objectif (WIN_THRESHOLD)
        // Fond jaune fluo et texte en majuscules
        // Création de l'ObjectivePanel dédié
        // Par exemple, si WIN_THRESHOLD vaut 20050 :
        objectiveWinnerPanel = new ObjectiveWinnerPanel(round.getWinThreshold());


        //layout and adding objects for GameStatePanel
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(roundProgressBar);
        add(objectivePanel);
        add(balanceLabel);
        add(objectiveWinnerPanel); // Ajout du label d'objectif général

        /* CustomButton storeButton = new CustomButton("Store");
        //when we click on the store button, a pop up window, shows up, with the items available in the store.
        storeButton.addActionListener(e -> {
            ((World) getParent().getParent()).showStore();
        });
        add(storeButton);
        */
    }

    private void update_objective_text(){
        AbstractMap.SimpleEntry<Integer, Integer> nbAS = farm.getNbAnimalsAndShepherds();
        objectiveFeeding.setText("Feeding Animals: " + round.getAnimalFeedingPrice(farm.getNbAnimalsAndShepherds().getKey()));
        objectiveShepherd.setText("+ Shepherd salaries: " + round.getShepherdSalary(farm.getNbAnimalsAndShepherds().getValue()));
        objectiveRent.setText("+ Rent: " + round.getRent());
        objectiveLoan.setText("+ Loan repayment: " + round.getLoanRepayment());
        objectiveTotal.setText("Total: " + round.getTotalPayment(farm.getNbAnimalsAndShepherds()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //roundProgressBar.paintComponent(g);
        balanceLabel.setText("Balance: " + farm.getBank().getBalance());
        update_objective_text();
    }

    public void connect(WorldController c){
        //pas utilisée
    }
}
