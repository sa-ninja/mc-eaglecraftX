package proclient.module.misc;


import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import proclient.module.Category;
import proclient.module.Module;

import java.util.Random;

public class Dupe extends Module {
    public Dupe() {
        super("Duper", KeyboardConstants.KEY_NONE, Category.MISC);
    }

    private final Random random = new Random();

    public void onEnable() {
        ItemStack held = mc.thePlayer.getHeldItem();
        if (held == null) {
            toggle();
            return;
        }

        int count = random.nextInt(31) + 1;

        for (int i = 0; i <= count; i++) {
            EntityItem entityItem = mc.thePlayer.dropItem(held.copy(), false, true);
            if (entityItem != null) mc.theWorld.addEntityToWorld(entityItem.getEntityId(), entityItem);
        }

        toggle();
    }
}