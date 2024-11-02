package l3oatz.eldoria.event;

import net.minecraft.client.Minecraft;

public class AutoThirdPerson
{
	public static boolean CANCEL_AUTO_RESTORE = true;
	public static int oldCameraMode = 0;
	
	public static void enterThirdPerson() {
		oldCameraMode = Minecraft.getMinecraft().gameSettings.thirdPersonView;
		setCameraMode(1);
		
	}
	
	public static void setCameraMode(int mode) {
		Minecraft.getMinecraft().gameSettings.thirdPersonView = mode;
	}
	
	public static int getCameraMode() {
		return Minecraft.getMinecraft().gameSettings.thirdPersonView;
	}
}