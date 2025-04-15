package Model.RoundManagement;

public enum GameStatus {
    RUNNING,
    BETWEEN_ROUNDS,
    GAME_OVER,

    WINNER,    // Nouveau statut indiquant que le joueur a gagné
    CLOSE,
}
