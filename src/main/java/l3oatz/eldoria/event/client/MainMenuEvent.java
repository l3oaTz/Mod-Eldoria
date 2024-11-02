package l3oatz.eldoria.event.client;

import l3oatz.eldoria.gui.ingame.GuiMain;
import l3oatz.eldoria.gui.ingame.dataframe.TimeTick;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MainMenuEvent
{
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void renderScreenPost(GuiOpenEvent event)
	{
	    /*if((event.getGui() instanceof GuiMainMenu))
	    {
	    	if(TimeTick.instance==null) {
				new TimeTick();
				TimeTick.instance.ticktime = 0;
				TimeTick.instance.isStop = false;
			}
	    	event.setGui(new GuiMain());
	    	TimeTick.instance.ticktime = 0;
			TimeTick.instance.isStop = false;
	    }*/
	}
}