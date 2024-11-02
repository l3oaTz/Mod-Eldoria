package l3oatz.eldoria.network;

import l3oatz.eldoria.EORReference;
import l3oatz.eldoria.network.client.PlayerStatusSyncPacket;
import l3oatz.eldoria.network.server.PacketOpenGuiServer;
import l3oatz.eldoria.network.server.PacketUpStatus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler
{
	public static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(EORReference.MODID);
	private static int packetId = 0;
	
	public static final void registerPackets()
	{
		registerMessage(PlayerStatusSyncPacket.Handler.class, PlayerStatusSyncPacket.class, Side.CLIENT);
		
		registerMessage(PacketOpenGuiServer.Handler.class, PacketOpenGuiServer.class, Side.SERVER);
		registerMessage(PacketUpStatus.Handler.class, PacketUpStatus.class, Side.SERVER);
	}
	
	@SuppressWarnings("unchecked")
	private static final void registerMessage(@SuppressWarnings("rawtypes") Class handlerClass, @SuppressWarnings("rawtypes") Class messageClass, Side side)
	{
		dispatcher.registerMessage(handlerClass, messageClass, packetId++, side);
	}
  
	public static final void sendTo(IMessage message, EntityPlayerMP player)
	{
		dispatcher.sendTo(message, player);
	}
  
	public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point)
	{
		dispatcher.sendToAllAround(message, point);
	}
  
	public static final void sendToAll(IMessage message)
	{
		dispatcher.sendToAll(message);
	}
  
	public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range)
	{
		sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
	}
  
	public static final void sendToAllAround(IMessage message, EntityPlayer player, double range)
	{
		sendToAllAround(message, player.world.provider.getDimension(), player.posX, player.posY, player.posZ, range);
	}
  
	public static final void sendToDimension(IMessage message, int dimensionId)
	{
		dispatcher.sendToDimension(message, dimensionId);
	}
  
	public static final void sendToServer(IMessage message)
	{
		dispatcher.sendToServer(message);
	}
}