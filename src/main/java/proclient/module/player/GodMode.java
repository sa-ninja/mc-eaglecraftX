package proclient.module.player;

import proclient.module.Category;
import proclient.module.Module;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.gui.GuiGameOver;

public class GodMode extends Module {
    public GodMode() {
        super("GodMode", KeyboardConstants.KEY_NONE, Category.PLAYER);
    }

    @Override
    public void onEnable() {
        if (mc.thePlayer == null) return;
        mc.thePlayer.capabilities.disableDamage = true;
        mc.thePlayer.setHealth(mc.thePlayer.getMaxHealth());
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (mc.thePlayer != null) {
            mc.thePlayer.capabilities.disableDamage = false;
        }
        super.onDisable();
    }

    @Override
    public void onUpdate() {
        if (!this.isToggled() || mc.thePlayer == null) return;
        mc.thePlayer.capabilities.disableDamage = true;
        if (mc.thePlayer.getHealth() < mc.thePlayer.getMaxHealth()) {
            mc.thePlayer.setHealth(mc.thePlayer.getMaxHealth());
        }
        if (mc.currentScreen instanceof GuiGameOver) {
            mc.currentScreen = null;
        }
    }
}
