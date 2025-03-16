package View;

import Model.Resources.Milk;
import Model.Resources.Resource;

import javax.swing.*;

public class ResourceBarPanel extends JPanel {

    //création d'une variable de type JProgressBar
    private JProgressBar progressBar;

    //création d'une variable Resource
    private Resource resource;

    /** Classe qui indique quand la ressource associée à ResourceBarPanel est prête
     * à l'aide d'une barre de progression (basée sur cpt de ResourceCooldownThread)
     */

    //création du constructeur
    public ResourceBarPanel(Resource r) {
        //création de la barre de progression
        progressBar = new JProgressBar();
        resource = r;
        progressBar.setValue(resource.get_cooldown());
        progressBar.setMaximum(resource.get_cooldown_max());
        //progressBar.setStringPainted(true);
        add(progressBar);
    }

    //fonction qui met à jour la barre de progression avec repaint
    public void update() {
        progressBar.setValue(resource.get_cooldown());
        repaint();
    }

    //création d'un main pour tester l'affichage :
    // - crée une ressource de type Resource,
    // - crée un ResourceBarPanel associé à cette Resource
    // - affiche le ResourceBarPanel
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
