package l3oatz.eldoria;

import l3oatz.eldoria.event.client.InterfaceHandler;
import l3oatz.eldoria.event.client.KeyInputHandler;
import l3oatz.eldoria.event.client.MainMenuEvent;
import l3oatz.eldoria.event.client.TickEventHandler;
import l3oatz.eldoria.gui.EORHeadBarRenderer;
import l3oatz.eldoria.gui.ingame.dataframe.DataFrame;
import l3oatz.eldoria.gui.ingame.dataframe.ImageUtils;
import l3oatz.eldoria.gui.ingame.dataframe.TimeTick;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends ServerProxy
{
	@SideOnly(Side.CLIENT)
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void preInit()
	{
		new TimeTick();
		new ImageUtils();
		new DataFrame();
		KeyInputHandler.initKeyBindings();
    	MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        MinecraftForge.EVENT_BUS.register(new TickEventHandler());
        MinecraftForge.EVENT_BUS.register(new EORHeadBarRenderer());
	}
	
	public void init()
    {

        MinecraftForge.EVENT_BUS.register(new MainMenuEvent());
        MinecraftForge.EVENT_BUS.register(new InterfaceHandler());
    }
}