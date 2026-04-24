package proclient.module.player;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import proclient.module.Category;
import proclient.module.Module;

public class NeverHungry extends Module {
    public NeverHungry() {
        super("NeverHungry", KeyboardConstants.KEY_NONE, Category.PLAYER);
    }

    @Override
    public void onUpdate() {
        if (!this.isToggled() || mc.thePlayer == null) return;
        mc.thePlayer.getFoodStats().setFoodLevel(20);
        mc.thePlayer.getFoodStats().setFoodSaturationLevel(5.0f);
    }
}
