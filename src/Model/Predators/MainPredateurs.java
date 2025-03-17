package Model.Predators;

import Model.Entity;
import Model.Farm;

import java.util.Iterator;

public class MainPredateurs {

    public static void main(String[] args) {
        // Cr�e la ferme qui initialise le terrain et les entit�s
        Farm farm = new Farm();

        // Exemple : it�rer sur les entit�s de la ferme et afficher leur nom
        Iterator<Entity> it = farm.getEntities();
        while (it.hasNext()) {
            Entity e = it.next();
            System.out.println("Entit� pr�sente : " + e.getName());
        }

        // Vous pouvez ici ajouter la logique de d�tection de proximit�,
        // de d�placement des entit�s, et d'interactions (par exemple, la fuite des pr�dateurs).
    }
}
