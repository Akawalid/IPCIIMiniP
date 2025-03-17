package Model.Resources;

public class Egg extends Resource{
    @Override
    public int get_cooldown_max() {
        return COOLDOWN_MAX_EGG;
    }

    @Override
    protected boolean get_ready_on_purchase() {
        return false;
    }

    @Override
    public String get_name() {
        return "Egg";
    }
}
