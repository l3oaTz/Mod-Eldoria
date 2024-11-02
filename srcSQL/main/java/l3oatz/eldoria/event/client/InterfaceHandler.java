package l3oatz.eldoria.event.client;

import l3oatz.eldoria.gui.EORInterfaceBarGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InterfaceHandler
{
	private EORInterfaceBarGui customGui;

    public InterfaceHandler() {
        this.customGui = new EORInterfaceBarGui(Minecraft.getMinecraft());
    }
    

	
	/*@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onDisable(RenderGameOverlayEvent.Pre event)
	{
		if (((event.getType() == ElementType.HEALTH)
		|| (event.getType() == ElementType.ARMOR)
		|| (event.getType() == ElementType.EXPERIENCE)
		|| (event.getType() == ElementType.FOOD)
		|| (event.getType() == ElementType.HOTBAR))
		&& (event.isCancelable()))
		{
			event.setCanceled(true);
		}
	}*/

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL)
        {
            // Prevent the original GuiIngame from rendering
            //event.setCanceled(true);

            // Render the custom GUI
            customGui.renderGameOverlay(event.getPartialTicks());
        }
    }
}