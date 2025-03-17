package Model.Predators;

import Model.Entity;
import Model.Farm;

import java.util.Iterator;

public class MainPredateurs {

    public static void main(String[] args) {
        // Crée la ferme qui initialise le terrain et les entités
        Farm farm = new Farm();

        // Exemple : itérer sur les entités de la ferme et afficher leur nom
        Iterator<Entity> it = farm.getEntities();
        while (it.hasNext()) {
            Entity e = it.next();
            System.out.println("Entité présente : " + e.getName());
        }

        // Vous pouvez ici ajouter la logique de détection de proximité,
        // de déplacement des entités, et d'interactions (par exemple, la fuite des prédateurs).
    }
}
