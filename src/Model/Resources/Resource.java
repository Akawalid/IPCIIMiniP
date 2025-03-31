package Model.Resources;

import Model.Bank;
import Model.Exceptions.UnauthorizedAction;

public abstract class Resource {
    protected static int COOLDOWN_MAX_MILK = 10;
    protected static int COOLDOWN_MAX_WOOL = 10;
    protected static int COOLDOWN_MAX_EGG = 10;

    protected boolean is_ready;

    ResourceCooldownThread cooldown_thread;

    public Resource(){
        //thread cooldown
        cooldown_thread = new ResourceCooldownThread(this, get_ready_on_purchase());
        cooldown_thread.start();
    }

    protected abstract boolean get_ready_on_purchase();
    public abstract int get_cooldown_max();

    protected void set_ready(boolean b){
        is_ready = b;
    }
    public void collect() throws UnauthorizedAction {
        if(is_ready){
            cooldown_thread.start_new_cooldown(); //creates a new cooldown
        }
        else{
            throw new UnauthorizedAction("Impossible to collect this resource: timer not ready.");
        }
    }

    public int get_cooldown(){
        return cooldown_thread.get_cooldown();
    }

    public abstract String get_name();

    public int get_selling_price(){
        return Bank.get_price(this);
    }
}
