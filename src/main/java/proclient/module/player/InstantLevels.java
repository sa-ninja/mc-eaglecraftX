package proclient.module.player;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import proclient.module.Category;
import proclient.module.Module;

public class InstantLevels extends Module {
    public InstantLevels() {
        super("InstantLevels", KeyboardConstants.KEY_NONE, Category.PLAYER);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().thePlayer.experienceLevel = 35;
        super.onEnable();
    }
}
