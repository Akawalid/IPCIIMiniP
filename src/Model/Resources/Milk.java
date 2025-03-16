package Model.Resources;

public class Milk extends Resource{
    @Override
    public int get_cooldown_max() {
        return COOLDOWN_MAX_MILK;
    }

    @Override
    protected boolean get_ready_on_purchase() {
        return false;
    }
}
