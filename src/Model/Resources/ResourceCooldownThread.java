package Model.Resources;

public class ResourceCooldownThread extends Thread{

    private static final int DELAY = 1000; //in ms
    private static final int CD_INCREMENT = 1;
    private static final int CD_INIT = 0;

    private boolean active = true;
    private boolean is_ready;

    private final Resource resource;

    private int cooldown;
    private final int cooldown_max;
    public ResourceCooldownThread(Resource r, boolean ready){
        resource = r;
        cooldown_max = resource.get_cooldown_max();
        if(ready){ //the resource is already available
            cooldown = cooldown_max;
            this.set_ready();
        }
        else{
            cooldown = CD_INIT;
            is_ready = false;
        }
    }

    public void stopThread(){
        active = false;
    }

    private void set_ready(){
        is_ready = true;
        resource.set_ready(true);
    }

    protected void start_new_cooldown(){
        cooldown = CD_INIT;
        is_ready = false;
        resource.set_ready(false);
    }

    protected int get_cooldown(){
        return Math.min(cooldown, cooldown_max);
    }

    @Override
    public void run(){
        while(active){
            try { //attente de 1 seconde
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(cooldown < cooldown_max){
                //increment cooldown
                cooldown += CD_INCREMENT;
            }
            if(cooldown >= cooldown_max && !is_ready){
                this.set_ready();
            }
        }

    }

}
