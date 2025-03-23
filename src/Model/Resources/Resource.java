package Model.Resources;

import Model.Exceptions.UnauthorizedCollection;

public abstract class Resource {
    protected static int COOLDOWN_MAX_MILK = 10;
    protected static int COOLDOWN_MAX_WOOL = 10;
    protected static int COOLDOWN_MAX_EGG = 10;

    protected boolean is_ready;

    ResourceCooldownThread cooldown_thread;

    public Resource(){
        //thread cooldown
        if(get_ready_on_purchase()){
            //the resource is already available
            //is_ready = true;
            cooldown_thread = new ResourceCooldownThread(this, get_cooldown_max());
            cooldown_thread.start();
            //TODO y revenir
        }
        else{
            start_cooldown();
        }
    }

    protected abstract boolean get_ready_on_purchase();
    public abstract int get_cooldown_max();
    private void start_cooldown(){
        cooldown_thread = new ResourceCooldownThread(this);
        cooldown_thread.start();
    }

    protected void set_ready(){
        is_ready = true;
    }
    public void collect() throws UnauthorizedCollection {
        if(is_ready){
            start_cooldown(); //creates a new cooldown
            is_ready = false;
        }
        else{
            throw new UnauthorizedCollection();
        }
    }

    public int get_cooldown(){
        return cooldown_thread.get_cooldown();
    }

    public abstract String get_name();
}
