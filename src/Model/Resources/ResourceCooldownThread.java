package Model.Resources;

public class ResourceCooldownThread extends Thread{

    private static final int DELAY = 1000; //in ms
    private static final int CD_INCREMENT = 1;
    private boolean active = true;

    private final Resource resource;

    private int cooldown;
    private final int cooldown_max;
    public ResourceCooldownThread(Resource r){

        this(r, 0);
    }

    public ResourceCooldownThread(Resource r, int init){
        resource = r;
        cooldown = init;
        cooldown_max = resource.get_cooldown_max();
    }

    protected int get_cooldown(){
        return Math.min(cooldown, cooldown_max);
    }

    @Override
    public void run(){
        while(cooldown < cooldown_max) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //increment cooldown
            cooldown += CD_INCREMENT;
        }
        //cooldown expired
        resource.set_ready();
    }

}
