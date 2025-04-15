package Model.Position;

import Model.Farm;

public class TestAdjacentFreeSpot {
    public static void main(String[] args) {
        // Cr�er une instance de Farm (la grille)
        Farm farm = new Farm();

        // Choisir un spot central, par exemple (5, 5)
        Spot center = farm.getSpot(5, 5);
        System.out.println("Testing for spot at (" + center.getRow() + ", " + center.getCol() + ")");

        // 1. Cas initial : tous les spots adjacents sont traversables (libres)
        Spot freeSpot = farm.getAdjascentFreeUnsecureSpot(center);
        if (freeSpot != null) {
            System.out.println("Free adjacent spot found: (" + freeSpot.getRow() + ", " + freeSpot.getCol() + ")");
        } else {
            System.out.println("No free adjacent spot found.");
        }

        // 2. Marquer tous les spots adjacents comme non traversables
        for (int i = center.getRow() - 1; i <= center.getRow() + 1; i++) {
            for (int j = center.getCol() - 1; j <= center.getCol() + 1; j++) {
                // Ignorer le spot central lui-m�me
                if (i == center.getRow() && j == center.getCol()) continue;
                // V�rifier que les coordonn�es sont valides
                if (i >= 0 && i < Farm.HEIGHT && j >= 0 && j < Farm.WIDTH) {
                    Spot candidate = farm.getSpot(i, j);
                    candidate.setIsTraversable(false);
                    System.out.println("Marking spot (" + candidate.getRow() + ", " + candidate.getCol() + ") as occupied.");
                }
            }
        }

        // Rechercher un spot libre apr�s avoir marqu� les voisins
        Spot freeSpotAfter = farm.getAdjascentFreeUnsecureSpot(center);
        if (freeSpotAfter != null) {
            System.out.println("After marking adjacent spots, free spot found: (" +
                    freeSpotAfter.getRow() + ", " + freeSpotAfter.getCol() + ")");
        } else {
            System.out.println("After marking adjacent spots, no free adjacent spot found.");
        }
    }
}
