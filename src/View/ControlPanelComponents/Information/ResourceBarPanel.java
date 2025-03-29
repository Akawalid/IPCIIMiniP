package View.ControlPanelComponents.Information;

import Model.Resources.Milk;
import Model.Resources.Resource;
import View.CustomButton;
import Controller.Controller;

import javax.swing.*;

public class ResourceBarPanel extends JPanel {

    //création d'une variable de type JProgressBar
    private JProgressBar progressBar;

    //boutons
    private final CustomButton collectButton;

    //création d'une variable Resource
    private Resource resource;

    /** Classe qui indique quand la ressource associée à ResourceBarPanel est prête
     * à l'aide d'une barre de progression (basée sur cpt de ResourceCooldownThread)
     */

    //création du constructeur
    public ResourceBarPanel(Resource r) {
        resource = r;

        //créer un FlowLayout avec le nom de la ressource, la barre de progression et un bouton
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel resourceLabel = new JLabel(resource.get_name());
        add(resourceLabel);

        //création de la barre de progression
        progressBar = new JProgressBar();
        progressBar.setValue(resource.get_cooldown());
        progressBar.setMaximum(resource.get_cooldown_max());
        add(progressBar);

        //ajouter un bouton qui collecte la ressource
        collectButton = new CustomButton("Collect");
        add(collectButton);
    }

    public void connect(Controller c){
        collectButton.addActionListener(c.getResourceCollectHandler(resource));
    }

    /** fonction qui met à jour la barre de progression avec repaint */
    public void update() {
        progressBar.setValue(resource.get_cooldown());
        repaint();
    }

    /** création d'un main pour tester l'affichage :
     * - crée une ressource de type Resource,
     * - crée un ResourceBarPanel associé à cette Resource
     * - affiche le ResourceBarPanel
     */
    public static void main(String[] args) {
        Resource r = new Milk();
        ResourceBarPanel rbp = new ResourceBarPanel(r);
        JFrame frame = new JFrame();
        //créer un layout pour pouvoir afficher la barre de progression et le bouton
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //ajouter la barre de progression
        frame.add(rbp);

        //ajouter un bouton pour collecter la ressource
        JButton collectButton = new JButton("Collect");
        collectButton.addActionListener(e -> {
            try {
                r.collect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        frame.add(collectButton);

        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //actualiser la fenêtre
        while(true){
            rbp.update();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
