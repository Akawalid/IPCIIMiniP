package Model.Resources;

import Model.Exceptions.UnauthorizedAction;

public abstract class Resource {
    private final int PRICE, COOL_DOWN;
    protected boolean is_ready;

    ResourceCooldownThread cooldown_thread;

    public Resource(int price, int cooldown){
        //thread cooldown
        this.PRICE = price;
        this.COOL_DOWN = cooldown;
        cooldown_thread = new ResourceCooldownThread(this, get_ready_on_purchase());
        cooldown_thread.start();
    }

    protected abstract boolean get_ready_on_purchase();
    public int get_cooldown_max(){return COOL_DOWN;};

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
        return PRICE;
    }

    /** Fonction stopProductionThread */
    public void pauseProductionThread() {
        cooldown_thread.pauseThread();
    }

    public void resumeProductionThread() {
        cooldown_thread.resumeThread();
    }
}
