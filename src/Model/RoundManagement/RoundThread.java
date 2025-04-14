package Model.RoundManagement;

public class RoundThread extends Thread {

    /* Attributs :
    int DELAY : le temps d’actualisation du thread
    int CD_MAX : la valeur maximale du cooldown
    int CD_INCREMENT : la valeur d’incrément du cooldown
    int cooldown : la valeur du cooldown de manche
    boolean wait : en attente de relance
    boolean active : indique si le thread a été lancé
     */
    private static final int DELAY = 100;
    private static final int CD_MAX = 600;
    private static final int CD_INCREMENT = 1;
    private int cooldown;
    private boolean pause = false;
    private boolean active;

    private Round round;

    /** Constructeur */
    public RoundThread(Round r) {
        this.round = r;
        this.active = true;
    }

    public int getCooldownMax(){
        return CD_MAX;
    }

    public int getCooldown() {
        return cooldown;
    }

    /** Fonction stopThread() */
    public void stopThread() {
        this.active = false;
    }

    protected void pauseThread() {
        this.pause = true;
    }

    protected void resumeThread() {
        this.cooldown = 0;
        this.pause = false;
    }

    public void run() {
        while (active) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (cooldown < CD_MAX) {
                cooldown += CD_INCREMENT;
            } else {
                if (!pause) {
                    pause = true;
                    round.end_round();
                }
            }
        }
    }

}
