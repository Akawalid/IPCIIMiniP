# IPCII Projet

## Fonctionnalités implémentées

### 1. **Déplacements des éleveurs**
Les déplacements des éleveurs utilisent l'algorithme A* pour trouver un chemin entre deux positions sur le terrain.

- **Tests fournis** :
    - **Test 1** :  
      Classe : `Model.Shepherd.FindPath`  
      Objectif : Tester l'algorithme A* pour trouver un chemin.
    - **Test 2** :  
      Classe : `View.Main`  
      Objectif : Tester la fonctionnalité complète, du modèle à l'interface graphique.  
      **Remarque** : Le contrôleur n'est pas finalisé. Actuellement, on peut déplacer un éleveur d'un point A à un point B de manière statique.

- **Procédure pour tester** :
    1. Dans le constructeur de `View.ControlPanelComponents.ControlPanel`, commenter la ligne :
       ```java
       // setActiveEntity(new Sheep("Lucie"));
       ```
    2. Décommenter la ligne au-dessus :
       ```java
       setActiveEntity(new Shepherd("John"));
       ```
    3. Exécuter la méthode `main` de la classe `Main`.
    4. Cliquer sur le bouton "Move" pour déclencher l'animation de déplacement.

---

### 2. **Fenêtre graphique**
- **Description** :  
  La fenêtre graphique s'affiche lors de l'exécution de la méthode `main` de la classe `Main`.

- **Procédure pour tester** :
    1. Exécuter la méthode `main` de la classe `Main`.
    2. La fenêtre graphique s'affiche.

---

## Remarques
- Le contrôleur n'est pas encore finalisé en raison de la complexité de la fonctionnalité.
- Les tests actuels valident les déplacements des éleveurs et l'affichage de la fenêtre graphique.
- Nous avons décidé de modifier la structure de la classe Land qui affichait la grille en utilisant un GridLayout, une approche très complexe. Nous avons opté pour dessiner le terrain de manière standard en utilisant Graphics g.
- Les tests JUnit ont été commentés dans Model > FarmAnimal > Test > FarmAnimalAgeTest car ils ne compilaient pas pour deux d'entre nous.