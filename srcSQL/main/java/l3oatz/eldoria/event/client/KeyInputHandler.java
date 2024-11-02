package l3oatz.eldoria.event.client;

import org.lwjgl.input.Keyboard;

import l3oatz.eldoria.event.AutoThirdPerson;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyInputHandler
{
	public static KeyBinding statusKey = new KeyBinding("Status", Keyboard.KEY_H, "key.categories.gameplay");
	
	public static void initKeyBindings()
	{
		ClientRegistry.registerKeyBinding(statusKey);
	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if (AutoThirdPerson.CANCEL_AUTO_RESTORE && Minecraft.getMinecraft().gameSettings.keyBindTogglePerspective.isKeyDown()) {
			AutoThirdPerson.oldCameraMode = -1;
		}
	}
}