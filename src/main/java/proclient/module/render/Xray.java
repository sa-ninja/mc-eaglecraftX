package proclient.module.render;

import java.util.ArrayList;

import proclient.module.Category;
import proclient.module.Module;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Xray extends Module {
    public static boolean enabled;
    public static Xray instance;
    private float oldGamma;
    private int oldAO;
    public ArrayList<Block> xrayBlocks = new ArrayList<Block>();

    public Xray() {
        super("X-Ray", KeyboardConstants.KEY_NONE, Category.RENDER);
        instance = this;
    }

    private void populateXrayBlocks() {
        xrayBlocks.clear();
        xrayBlocks.add(Blocks.diamond_ore);
        xrayBlocks.add(Blocks.gold_ore);
        xrayBlocks.add(Blocks.iron_ore);
        xrayBlocks.add(Blocks.coal_ore);
        xrayBlocks.add(Blocks.lapis_ore);
        xrayBlocks.add(Blocks.emerald_ore);
        xrayBlocks.add(Blocks.redstone_ore);
        xrayBlocks.add(Blocks.lit_redstone_ore);
        xrayBlocks.add(Blocks.quartz_ore);
        xrayBlocks.add(Blocks.obsidian);
        xrayBlocks.add(Blocks.mob_spawner);
        xrayBlocks.add(Blocks.chest);
        xrayBlocks.add(Blocks.trapped_chest);
        xrayBlocks.add(Blocks.ender_chest);
    }

    @Override
    public void onEnable() {
        populateXrayBlocks();
        oldGamma = mc.gameSettings.gammaSetting;
        oldAO = mc.gameSettings.ambientOcclusion;
        mc.gameSettings.gammaSetting = 10.0f;
        mc.gameSettings.ambientOcclusion = 0;
        enabled = true;
        mc.renderGlobal.loadRenderers();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        enabled = false;
        mc.gameSettings.gammaSetting = oldGamma;
        mc.gameSettings.ambientOcclusion = oldAO;
        mc.renderGlobal.loadRenderers();
        super.onDisable();
    }

    public boolean shouldXrayBlock(Block block) {
        return xrayBlocks.contains(block);
    }
}
