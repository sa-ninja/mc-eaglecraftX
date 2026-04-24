package proclient.module.player;

import java.util.ArrayList;

import net.lax1dude.eaglercraft.v1_8.Keyboard;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import proclient.Dragon;
import proclient.event.events.EventUpdate;
import proclient.module.Category;
import proclient.module.Module;
import proclient.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AllowFly extends Module{
	private final Setting mode = new Setting("Fly Mode", this, "Vanilla", "Watchdog", "Viper");
	public AllowFly(){
		super("Fly", KeyboardConstants.KEY_NONE, Category.PLAYER, true);
		addAll(mode);
	}
	
	public void onUpdate() {
		String mode = Dragon.setmgr.getSettingByName("Fly Mode").getMode();
		if (this.isToggled()) {
		if(mode.equalsIgnoreCase("Vanilla")) {
			mc.thePlayer.capabilities.isFlying = true;
            mc.thePlayer.capabilities.allowFlying = true;
            mc.thePlayer.capabilities.setFlySpeed(0.05F);
        	} else if(mode.equalsIgnoreCase("Viper")) {
				if(mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0) {
					double speed = 0.2D;
					float yaw = (float) Math.toRadians(mc.thePlayer.rotationYaw);
					mc.thePlayer.motionX = -Math.sin(yaw) * speed;
					mc.thePlayer.motionZ = Math.cos(yaw) * speed;
					if(mc.gameSettings.keyBindJump.isKeyDown()) {
						mc.thePlayer.motionY = 0.5F;
					} else if(mc.gameSettings.keyBindSneak.isKeyDown()) {
						mc.thePlayer.motionY = -0.5F;
					}
					
					if(!mc.gameSettings.keyBindSneak.isKeyDown() && !mc.gameSettings.keyBindJump.isKeyDown()) {
						mc.thePlayer.motionY = 0;
					}
					
				} else {
					mc.thePlayer.setVelocity(0, 0, 0);
				}
			} else if(mode.equalsIgnoreCase("Watchdog")) {
				if (mc.thePlayer.onGround) {
				    final double offset = 0.4122222218322211111111F;
					final NetHandlerPlayClient netHandler = mc.getNetHandler();
					final EntityPlayerSP player = mc.thePlayer;
					final double x = player.posX;
					final double y = player.posY;
					final double z = player.posZ;
					
					for (int i = 0; i < 9; i++) {
						netHandler.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + offset, z, false));
						netHandler.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.000002737272, z, false));
						netHandler.addToSendQueue(new C03PacketPlayer(false));
					}
					netHandler.addToSendQueue(new C03PacketPlayer(true));
				}
			}
		}
	}
	
	public void onDisable(){
		Minecraft.getMinecraft().thePlayer.capabilities.allowFlying = false;
		Minecraft.getMinecraft().thePlayer.capabilities.isFlying = false;
		super.onDisable();
	}

}