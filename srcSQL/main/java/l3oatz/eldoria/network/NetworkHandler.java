package l3oatz.eldoria.network;

import l3oatz.eldoria.EORReference;
import l3oatz.eldoria.network.client.SyncPlayerDataPacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(EORReference.MODID);
	private static int packetId = 0;
	
	public static final void registerPackets()
	{
		INSTANCE.registerMessage(SyncPlayerDataPacket.Handler.class, SyncPlayerDataPacket.class, packetId++, Side.CLIENT);
	}
}