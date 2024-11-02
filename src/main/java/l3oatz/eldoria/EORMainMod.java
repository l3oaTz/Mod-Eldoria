package l3oatz.eldoria;

import l3oatz.eldoria.capabilities.CapabilitiesInit;
import l3oatz.eldoria.command.CommandCheckStatus;
import l3oatz.eldoria.event.PlayerStatusHandler;
import l3oatz.eldoria.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = EORReference.MODID, name = EORReference.NAME, version = EORReference.VERSION)
public class EORMainMod
{
	private static int modGuiIndex = 1;
	public static final int GUI_INVENTORY = modGuiIndex++;
	@SidedProxy(clientSide = "l3oatz.eldoria.ClientProxy", serverSide = "l3oatz.eldoria.ServerProxy")
    public static ServerProxy proxy;
	
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
	{
		CapabilitiesInit.registerCapabilities();
		NetworkHandler.registerPackets();
        proxy.preInit();
        if (event.getSide().isClient()) {
        	
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new PlayerStatusHandler());
        proxy.init();
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event)
    {
        proxy.onServerStarting();
        event.registerServerCommand(new CommandCheckStatus());
    }
}