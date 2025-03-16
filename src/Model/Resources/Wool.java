package Model.Resources;

public class Wool extends Resource{
    @Override
    public int get_cooldown_max() {
        return COOLDOWN_MAX_WOOL;
    }

    @Override
    protected boolean get_ready_on_purchase() {
        return true;
    }
}
